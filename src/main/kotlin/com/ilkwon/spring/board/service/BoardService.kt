package com.ilkwon.spring.board.service

import com.ilkwon.spring.board.dto.BoardCreateRequest
import com.ilkwon.spring.board.dto.BoardDetailResponse
import com.ilkwon.spring.board.dto.BoardListResponse
import com.ilkwon.spring.board.dto.BoardUpdateRequest
import com.ilkwon.spring.board.dto.exception.BoardNotFoundException
import com.ilkwon.spring.board.dto.exception.NotUpdateException
import com.ilkwon.spring.board.dto.exception.UserDeniedException
import com.ilkwon.spring.board.entity.Board
import com.ilkwon.spring.board.repository.BoardRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class BoardService(private val boardRepository: BoardRepository) {

    //title, content, writer, create_at, updated_at, UUID
    fun createBoard(
        request: BoardCreateRequest,
        writerId: Long
    ): Board {
        val board = Board.create(
            title = request.title,
            content = request.content,
            writerId = writerId
        )
        return boardRepository.save(board)
    }

    fun getBoardDetail(
        uuid: String
    ): BoardDetailResponse {

        boardRepository.increaseViews(uuid)

        val board = boardRepository.findByUuid(uuid)
            ?: throw BoardNotFoundException(uuid)

        return BoardDetailResponse.detail(board)
    }

    fun getBoardList(
        page: Int,
        size: Int,
        sort: String
    ): List<BoardListResponse>{
        val page = if (page < 1) 1 else page
        //controler 에서 받아온 sort에 따라 (유저가 입력했다 가정) 조회소순, 오래된순, 최신순 선택
        val sort = when (sort){
            "views"  -> Sort.by(Sort.Direction.DESC, "views")
            "oldest" -> Sort.by(Sort.Direction.DESC, "oldest")
            else     -> Sort.by(Sort.Direction.DESC, "createdAt") //최신순 -> 기본값
        }
        val pageable = PageRequest.of(page-1, size, sort)

        return boardRepository.findAll(pageable).content.map(BoardListResponse::boardList)
    }

    fun updateBoard(
        uuid: String,
        request: BoardUpdateRequest,
        writerId: Long
    ){
        val board = boardRepository.findByUuid(uuid)
            ?: throw BoardNotFoundException(uuid)

        require(
            board.writerId == writerId
        ) {
            throw UserDeniedException()
        }
        require(
            request.title != null || request.content != null
        ) {
            throw NotUpdateException()
        }
        val updatedBoard = board.update(request)
        boardRepository.save(updatedBoard)
    }

    fun deleteBoard(
        uuid: String,
        writerId: Long) {
        val board = boardRepository.findByUuid(uuid)
            ?: throw BoardNotFoundException(uuid)

        if (board.writerId != writerId) {
            throw UserDeniedException()
        }

        boardRepository.delete(board)
    }
}