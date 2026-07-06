package com._ilkwon.develop_board.board.repository

import com._ilkwon.develop_board.board.entity.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
public interface BoardRepository : JpaRepository<Board, Long> {
    fun findByUuid(uuid: String): Board?  // 상세 조회
    fun findAllByOrderByCreatedAtDesc(pageable: Pageable): Page<Board>
    fun findAllByOrderByCreatedAtAsc(pageable: Pageable): Page<Board>
    fun findAllByOrderByViewsDesc(pageable: Pageable): Page<Board>

    @Modifying
    @Query("UPDATE Board SET views = views + 1 WHERE uuid = :uuid")
    fun increaseViews(uuid: String)
}