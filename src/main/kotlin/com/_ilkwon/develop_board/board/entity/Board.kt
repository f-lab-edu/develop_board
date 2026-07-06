package com._ilkwon.develop_board.board.entity

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
class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0;

    @Column(name = "title", length = 100)
    var title: String? = null

    @Column(name = "content", length = 2000)
    var content: String? = null

    @Column(name = "writer")
    var writer: Long? = null;

    @CreatedDate
    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null

    @Column(name = "uuid", nullable = false, unique = true, length = 36)
    val uuid : String = UUID.randomUUID().toString()
    // 가끔 binary16으로 한다는 블로그를 보았는데,, uuid를 varchar32와 binary16 중에서 실제로 어떤걸 쓰는지,,,?

    @Column(name = "views")
    var views : Long? = 0

    @Enumerated(EnumType.STRING)
    @Column(name = "tag", length = 20)
    var tag: ViewTag? = ViewTag.NORMAL

}