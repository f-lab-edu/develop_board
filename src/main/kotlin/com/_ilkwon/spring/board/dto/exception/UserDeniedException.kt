package com._ilkwon.spring.board.dto.exception

class UserDeniedException :
    RuntimeException("게시글을 수정할 권한이 없습니다.")