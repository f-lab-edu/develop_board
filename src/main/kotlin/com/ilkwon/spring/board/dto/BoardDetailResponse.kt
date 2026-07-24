package com.ilkwon.spring.board.dto

import com.ilkwon.spring.board.entity.Board
import com.ilkwon.spring.board.entity.ViewTag
import java.time.LocalDateTime


data class BoardDetailResponse(
    val title: String,
    val content: String,
    val writerId: Long,
    val updatedAt: LocalDateTime?,
    val views: Long,
    val tag: ViewTag
) {
    companion object {
        fun detail(board: Board): BoardDetailResponse {
            return BoardDetailResponse(
                title = board.title,
                content = board.content,
                writerId = board.writerId,
                updatedAt = board.updatedAt,
                views = board.views,
                tag = board.tag
            )
        }
    }
}