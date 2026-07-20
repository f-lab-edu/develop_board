package com.ilkwon.spring.board.dto

import com.ilkwon.spring.board.entity.ViewTag
import java.time.LocalDateTime

data class BoardDetailResponse(
    val title: String,
    val content: String,
    val writerId: Long,
    val updatedAt: LocalDateTime,
    val views: Long,
    val tag : ViewTag
    )