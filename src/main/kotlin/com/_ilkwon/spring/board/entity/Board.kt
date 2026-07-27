package com._ilkwon.spring.board.entity

import com._ilkwon.spring.board.dto.BoardUpdateRequest
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.UUID

@Table(name = "board")
@Entity
data class Board private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "title", length = 100, nullable = false)
    val title: String,

    @Column(name = "content", length = 2000, nullable = false)
    val content: String,

    @Column(name = "writer_id")
    val writerId: Long,

    @CreatedDate
    @Column(name = "created_at")
    val createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,

    @Column(name = "uuid", nullable = false, unique = true, length = 36)
    val uuid : String = UUID.randomUUID().toString(),
    // 가끔 binary16으로 한다는 블로그를 보았는데,, uuid를 varchar32와 binary16 중에서 실제로 어떤걸 쓰는지,,,?

    @Column(name = "views")
    val views : Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "tag", length = 20,  nullable = false)
    val tag: ViewTag = ViewTag.NORMAL
){
    companion object {
        fun create(
            title: String,
            content: String,
            writerId: Long,
        ): Board {
            require(title.isNotBlank()) {
                "제목을 입력해 주세요."
            }
            require(content.isNotBlank()) {
                "내용은 입력해 주세요."
            }

            return Board(
                title = title,
                content = content,
                writerId = writerId,
            )
        }
    }

    fun update(request: BoardUpdateRequest): Board {
        require(
            request.title != null || request.content != null
        ) {
            "수정하는 내용이 없습니다"
        }
        return copy(
            title = request.title ?: title,
            content = request.content ?: content,
        )
    }
}