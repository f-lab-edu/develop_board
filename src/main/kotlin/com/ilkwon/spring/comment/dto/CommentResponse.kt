package com.ilkwon.spring.comment.dto

import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val content: String,
    val writerId: Long,
    val createdAt: LocalDateTime,
    val children: List<CommentResponse>
)