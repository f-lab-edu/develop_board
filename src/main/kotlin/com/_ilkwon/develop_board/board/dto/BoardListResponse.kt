package com._ilkwon.develop_board.board.dto

import com._ilkwon.develop_board.board.entity.ViewTag
import java.time.LocalDateTime

data class BoardListResponse(
    val uuid: String,
    val title: String?,
    val views: Long?,
    val writer: Long?,
    val updatedAt: LocalDateTime?,
    val tag: ViewTag?
)