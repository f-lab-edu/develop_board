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
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

@Entity
@Table(name = "board")
data class Board private constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "title", length = 100, nullable = false)
    val title: String,

    @Column(name = "content", length = 2000, nullable = false)
    val content: String,

    @Column(name = "writer_id", nullable = false)
    val writerId: Long,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,

    @Column(name = "uuid", nullable = false, unique = true, length = 36)
    val uuid: String = UUID.randomUUID().toString(),

    @Column(name = "views", nullable = false)
    val views: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "tag", length = 20, nullable = false)
    val tag: ViewTag = ViewTag.NORMAL,

    @Column(name = "idempotency_key", nullable = false, unique = true)
    val idempotencyKey: String
) {

    companion object {
        fun create(
            title: String,
            content: String,
            writerId: Long,
            idempotencyKey: String
        ): Board {
            require(title.isNotBlank()) {
                "제목을 입력해 주세요."
            }
            require(content.isNotBlank()) {
                "내용을 입력해 주세요."
            }

            return Board(
                title = title,
                content = content,
                writerId = writerId,
                idempotencyKey = idempotencyKey
            )
        }
    }

    fun update(request: BoardUpdateRequest): Board {
        require(
            request.title != null || request.content != null
        ) {
            "수정하는 내용이 없습니다."
        }

        return copy(
            title = request.title ?: title,
            content = request.content ?: content
        )
    }
}