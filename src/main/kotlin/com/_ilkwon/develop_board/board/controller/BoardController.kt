package com._ilkwon.develop_board.board.controller

import com._ilkwon.develop_board.board.dto.BoardCreateRequest
import com._ilkwon.develop_board.board.dto.BoardDetailResponse
import com._ilkwon.develop_board.board.dto.BoardListResponse
import com._ilkwon.develop_board.board.dto.BoardUpdateRequest
import com._ilkwon.develop_board.board.entity.Board
import com._ilkwon.develop_board.board.service.BoardService
import com.oracle.svm.core.annotate.Delete
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping(("/api/posts"))
class BoardController (private val boardService: BoardService) {

    @PostMapping("/create")
    fun createBoard(@RequestHeader("x-note-account") writerId : Long,
                    @RequestBody request: BoardCreateRequest
    ): String {
            boardService.createBoard(request, writerId)
            return "board create success 입니다."
    }

    @GetMapping("/{uuid}")
    fun getBoardDetail(@PathVariable uuid: String): BoardDetailResponse{
        return boardService.getBoardDetail(uuid)
    }

    @GetMapping
    fun getBoardList(
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "30") size: Int,
        @RequestParam(defaultValue = "latest") sort: String
    ): List<BoardListResponse> {
        return boardService.getBoardList(page, size, sort)
    }


    @PutMapping("/update")
    fun updateBoard(
        @RequestHeader("x-note-account") writerId: Long,
        @RequestBody request: BoardUpdateRequest
    ): String {
        boardService.updateBoard(request)
        return "board update success 입니다"
    }

    @DeleteMapping("/{uuid}")
    fun deleteBoard(@PathVariable uuid: String): String {
        boardService.deleteBoard(uuid)
        return "board delete success 입니다"
    }
}