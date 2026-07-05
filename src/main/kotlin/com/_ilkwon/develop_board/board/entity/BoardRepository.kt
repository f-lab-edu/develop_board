package com._ilkwon.develop_board.board.entity

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface BoardRepository : JpaRepository<Board, Long> {
    fun findByUuid(uuid: String): Board?  // 상세 조회
    fun findAllByOrderByCreatedAtDesc(): List<Board> //최신순
    fun findAllByOrderByCreatedAtAsc(): List<Board> //오래된 순
    fun findAllByOrderByViewsDesc(): List<Board> // 조회수 많은 순
}