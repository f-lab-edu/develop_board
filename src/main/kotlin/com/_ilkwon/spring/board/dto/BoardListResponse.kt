package com._ilkwon.spring.board.dto

import com._ilkwon.spring.board.entity.Board
import com._ilkwon.spring.board.entity.ViewTag
import java.time.LocalDateTime

data class BoardListResponse(
    val uuid: String,
    val title: String,
    val views: Long,
    val writerId: Long,
    val updatedAt: LocalDateTime?,
    val tag: ViewTag
) {
    companion object {
        fun boardList(board: Board): BoardListResponse {
            return BoardListResponse(
                uuid = board.uuid,
                title = board.title,
                views = board.views,
                writerId = board.writerId,
                updatedAt = board.updatedAt,
                tag = board.tag
            )
        }
    }
}