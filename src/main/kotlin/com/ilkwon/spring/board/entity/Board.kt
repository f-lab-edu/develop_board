package com.ilkwon.spring.board.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID
import com.ilkwon.spring.board.dto.BoardCreateRequest
import com.ilkwon.spring.board.dto.BoardUpdateRequest
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

@Entity
@Table(name = "board")
class Board(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "title", length = 100, nullable = false)
    var title: String,

    @Column(name = "content", length = 2000, nullable = false)
    var content: String,

    @Column(name = "writer_id", nullable = false)
    val writerId: Long,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "uuid", nullable = false, unique = true, length = 36)
    val uuid: String = UUID.randomUUID().toString(),

    @Column(name = "views", nullable = false)
    var views: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "tag", length = 20, nullable = false)
    var tag: ViewTag = ViewTag.NORMAL,

    @Column(name = "idempotency_key", nullable = false, unique = true)
    val idempotencyKey: String
) {

    companion object {
        fun create(
            request: BoardCreateRequest,
            writerId: Long,
            idempotencyKey : String
        ): Board {
            return Board(
                title = request.title,
                content = request.content,
                writerId = writerId,
                idempotencyKey = idempotencyKey
            )
        }
    }

    fun update(request: BoardUpdateRequest) {
        request.title?.let {
            title = it
        }

        request.content?.let {
            content = it
        }
    }
}