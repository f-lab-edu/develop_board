package com._ilkwon.develop_board.board.controller

import com._ilkwon.develop_board.board.dto.BoardCreateRequest
import com._ilkwon.develop_board.board.entity.Board
import com._ilkwon.develop_board.board.service.BoardService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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

}