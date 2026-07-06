package com._ilkwon.develop_board.board.service

import com._ilkwon.develop_board.board.dto.BoardCreateRequest
import com._ilkwon.develop_board.board.dto.BoardDetailResponse
import com._ilkwon.develop_board.board.entity.Board
import com._ilkwon.develop_board.board.repository.BoardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Service
@Transactional
class BoardService(private val boardRepository: BoardRepository) {

    //title, content, writer, create_at, updated_at, UUID
    @Transactional
    fun createBoard(request: BoardCreateRequest, writerId: Long): Board {
        val board = Board().apply{
            title = request.title
            content = request.content
            writer = writerId
            createdAt = LocalDateTime.now()
            updatedAt = LocalDateTime.now()
        }
        return boardRepository.save(board)
    }

    @Transactional
    fun getBoardDetail(uuid: String): BoardDetailResponse {

        boardRepository.increaseViews(uuid)

        val board = boardRepository.findByUuid(uuid)

        return BoardDetailResponse(
            title = board?.title,
            content = board?.content,
            writer = board?.writer,
            updatedAt = board?.updatedAt,
            views = board?.views,
            tag = board?.tag
        )
    }

}