package com.ilkwon.spring.comment.service

import com.ilkwon.spring.board.dto.exception.BoardNotFoundException
import com.ilkwon.spring.board.repository.BoardRepository
import com.ilkwon.spring.comment.dto.CommentCreateRequest
import com.ilkwon.spring.comment.dto.CommentResponse
import com.ilkwon.spring.comment.dto.exception.CommentNotFoundException
import com.ilkwon.spring.comment.dto.exception.InvalidParentCommentException
import com.ilkwon.spring.comment.entity.Comment
import com.ilkwon.spring.comment.repository.CommentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommentService(
    private val commentRepository: CommentRepository,
    private val boardRepository: BoardRepository
) {

    fun createComment(
        boardUuid: String,
        request: CommentCreateRequest,
        writerId: Long
    ): CommentResponse {

        val board = boardRepository.findByUuid(boardUuid)
            ?: throw BoardNotFoundException(boardUuid)

        val parent = request.parentId?.let { parentId ->
            commentRepository.findById(parentId)
                .orElseThrow {
                    CommentNotFoundException(parentId)
                }
        }

        if (parent != null && parent.board.id != board.id) {
            throw InvalidParentCommentException(parent.id)
        }

        val comment = Comment.create(
            content = request.content,
            writerId = writerId,
            board = board,
            parent = parent
        )

        val savedComment = commentRepository.save(comment)

        return CommentResponse(
            id = savedComment.id,
            content = savedComment.content,
            writerId = savedComment.writerId,
            createdAt = requireNotNull(comment.createdAt),
            children = emptyList()
        )
    }

    fun getCommentsByBoardUuid(
        boardUuid: String
    ): List<CommentResponse> {

        val board = boardRepository.findByUuid(boardUuid)
            ?: throw BoardNotFoundException(boardUuid)

        val comments =
            commentRepository.findAllByBoardIdOrderByCreatedAtAscIdAsc(board.id)

        val childrenMap = comments.groupBy { it.parent?.id }

        fun toResponse(comment: Comment): CommentResponse {
            return CommentResponse(
                id = comment.id,
                content = comment.content,
                writerId = comment.writerId,
                createdAt = requireNotNull(comment.createdAt),
                children = childrenMap[comment.id]
                    ?.map { toResponse(it) }
                    ?: emptyList()
            )
        }

        return childrenMap[null]
            ?.map { toResponse(it) }
            ?: emptyList()
    }
}