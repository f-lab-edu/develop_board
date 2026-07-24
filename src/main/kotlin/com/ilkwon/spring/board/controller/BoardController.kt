package com.ilkwon.spring.board.controller

import com.ilkwon.spring.board.dto.BoardCreateRequest
import com.ilkwon.spring.board.dto.BoardDetailResponse
import com.ilkwon.spring.board.dto.BoardListResponse
import com.ilkwon.spring.board.dto.BoardUpdateRequest
import com.ilkwon.spring.board.dto.common.CommonResponse
import com.ilkwon.spring.board.service.BoardService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(("/api/posts"))
class BoardController (private val boardService: BoardService) {

    @PostMapping
    fun createBoard(
        @RequestHeader("x-note-account") writerId : Long,
        @RequestBody request: BoardCreateRequest,
        @RequestHeader("Idempotency-Key") idempotencyKey: String
    ): CommonResponse {
        boardService.createBoard(request, writerId, idempotencyKey)
        return CommonResponse.success(
            code = 200,
            message = "게시글이 생성되었습니다"
        )
    }

    @GetMapping("/{uuid}")
    fun getBoardDetail(
        @PathVariable uuid: String
    ): BoardDetailResponse{
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


    @PatchMapping("/{uuid}")
    fun updateBoard(
        @RequestHeader("x-note-account") writerId: Long,
        @PathVariable uuid : String,
        @RequestBody request: BoardUpdateRequest
    ): CommonResponse {
        boardService.updateBoard(uuid, request, writerId)
        return CommonResponse.success(
                code = 200,
        message = "게시글이 수정되었습니다"
        )
    }

    @DeleteMapping("/{uuid}")
    fun deleteBoard(
        @PathVariable uuid: String
    ): CommonResponse {
        boardService.deleteBoard(uuid)
        return CommonResponse.success(
            code = 200,
            message = "게시글이 삭제되었습니다"
        )
    }
}