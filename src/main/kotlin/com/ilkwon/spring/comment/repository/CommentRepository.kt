package com.ilkwon.spring.comment.repository

import com.ilkwon.spring.comment.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findAllByBoardIdOrderByCreatedAtAscIdAsc(boardId: Long): List<Comment>
}