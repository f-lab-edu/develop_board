package com.ilkwon.spring.comment.dto

data class CommentCreateRequest(
    val content: String,
    val parentId: Long?
)