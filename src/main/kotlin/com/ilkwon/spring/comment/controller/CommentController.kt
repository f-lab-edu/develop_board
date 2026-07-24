package com.ilkwon.spring.comment.controller

import com.ilkwon.spring.board.dto.common.CommonResponse
import com.ilkwon.spring.comment.dto.CommentCreateRequest
import com.ilkwon.spring.comment.dto.CommentResponse
import com.ilkwon.spring.comment.entity.Comment
import com.ilkwon.spring.comment.service.CommentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/posts/{boardUuid}/comments")
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping
    fun createComment(
        @RequestHeader("x-note-account") writerId: Long,
        @PathVariable boardUuid: String,
        @RequestBody request: CommentCreateRequest
    ): CommentResponse {
        return commentService.createComment(
            boardUuid = boardUuid,
            request = request,
            writerId = writerId
        )
    }

    @GetMapping
    fun getComments(
        @PathVariable boardUuid: String
    ): List<CommentResponse> {
        return commentService.getCommentsByBoardUuid(boardUuid)
    }

}