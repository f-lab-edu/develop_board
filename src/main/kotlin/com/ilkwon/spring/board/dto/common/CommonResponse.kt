package com.ilkwon.spring.board.dto.common

data class CommonResponse(
    val state: Boolean,
    val code: Int,
    val message: String
) {
    companion object {
        fun success(
            code: Int,
            message: String
        ): CommonResponse {
            return CommonResponse(
                state = true,
                code = code,
                message = message
            )
        }
    }
}