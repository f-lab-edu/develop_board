package com.ilkwon.spring.board.dto.exception

class IncreaseViewException :
    RuntimeException("조회수가 증가되지 못했습니다")