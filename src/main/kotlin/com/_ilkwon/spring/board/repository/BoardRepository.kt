package com._ilkwon.spring.board.repository

import com._ilkwon.spring.board.entity.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
public interface BoardRepository : JpaRepository<Board, Long> {
    fun findByUuid(uuid: String): Board?

    @Modifying
    @Query("UPDATE Board SET views = views + 1 WHERE uuid = :uuid")
    fun increaseViews(uuid: String)
}