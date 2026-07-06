package com._ilkwon.develop_board.board.dto

data class BoardUpdateRequest(
    val uuid: String,
    val title: String,
    val content: String
)