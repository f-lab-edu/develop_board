package com.ilkwon.spring.board.repository

import com.ilkwon.spring.board.entity.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : JpaRepository<Board, Long> {
    fun findByUuid(uuid: String): Board?
    fun findByIdempotencyKey(idempotencyKey: String): Board?

    @Modifying
    @Query("UPDATE Board SET views = views + 1, tag = CASE WHEN views + 1 >= 1000 THEN 'POPULAR' ELSE tag END WHERE uuid = :uuid")
    fun increaseViews(uuid: String)
}