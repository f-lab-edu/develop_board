package com._ilkwon.develop_board.board.service

import com._ilkwon.develop_board.board.dto.BoardCreateRequest
import com._ilkwon.develop_board.board.dto.BoardDetailResponse
import com._ilkwon.develop_board.board.dto.BoardListResponse
import com._ilkwon.develop_board.board.dto.BoardUpdateRequest
import com._ilkwon.develop_board.board.entity.Board
import com._ilkwon.develop_board.board.repository.BoardRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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

    @Transactional
    fun getBoardList(page: Int, size: Int, sort: String): List<BoardListResponse> {

        val page = if (page < 1) 1 else page
        val pageable = PageRequest.of(page-1, size)

        //controler 에서 받아온 sort에 따라 (유저가 입력했다 가정) 조회소순, 오래된순, 최신순 선택
        val boardList = when (sort){
            "views"  -> boardRepository.findAllByOrderByViewsDesc(pageable)
            "oldest" -> boardRepository.findAllByOrderByCreatedAtAsc(pageable)
            else     -> boardRepository.findAllByOrderByCreatedAtDesc(pageable) //최신순 -> 기본값
        }

        return boardList.content.map { board ->
            BoardListResponse(
                uuid = board.uuid,
                title = board.title,
                views = board.views,
                writer = board.writer,
                updatedAt = board.updatedAt,
                tag = board.tag
            )
        }
    }

    @Transactional
    fun updateBoard(request : BoardUpdateRequest) {

        val board = boardRepository.findByUuid(request.uuid)

        board?.title = request.title
        board?.content = request.content

    }

    @Transactional
    fun deleteBoard(uuid: String) {
        val board = boardRepository.findByUuid(uuid)

        boardRepository.delete(board!!)
    }

}