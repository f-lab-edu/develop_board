package com._ilkwon.develop_board.board.controller

import com._ilkwon.develop_board.board.dto.BoardCreateRequest
import com._ilkwon.develop_board.board.dto.BoardDetailResponse
import com._ilkwon.develop_board.board.entity.Board
import com._ilkwon.develop_board.board.service.BoardService
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


}