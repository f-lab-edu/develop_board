package com._ilkwon.develop_board.board.dto

import com._ilkwon.develop_board.board.entity.ViewTag
import java.io.Writer
import java.time.LocalDateTime

data class BoardDetailResponse(
    val title: String?,
    val content: String?,
    val writer: Long?,
    val updatedAt: LocalDateTime?,
    val views: Long?,
    val tag : ViewTag?
    ) {

}