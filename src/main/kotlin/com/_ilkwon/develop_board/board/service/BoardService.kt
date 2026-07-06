package com._ilkwon.develop_board.board.service

import com._ilkwon.develop_board.board.dto.BoardCreateRequest
import com._ilkwon.develop_board.board.entity.Board
import com._ilkwon.develop_board.board.repository.BoardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

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

}