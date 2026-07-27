package com._ilkwon.spring.board.dto.exception

class NotUpdateException :
    RuntimeException("제목과 본문에 작성된 내용이 없습니다.")