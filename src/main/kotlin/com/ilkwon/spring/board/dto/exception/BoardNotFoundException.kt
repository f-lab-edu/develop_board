package com.ilkwon.spring.board.dto.exception

class BoardNotFoundException(
    uuid: String
) : RuntimeException(
    "게시글을 찾을 수 없습니다. uuid: $uuid"
)