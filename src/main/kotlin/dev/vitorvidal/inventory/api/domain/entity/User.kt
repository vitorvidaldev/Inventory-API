package dev.vitorvidal.inventory.api.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "\"user\"")
class User() {
    constructor(email: String, password: String) : this() {
        this.email = email
        this.password = password
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val userId: UUID = UUID.randomUUID()

    @Column(name = "email", unique = true)
    var email: String = ""

    @Column(name = "password")
    var password: String = ""

    @Column(name = "creation_date")
    val creationDate: LocalDateTime = LocalDateTime.now()

    @Column(name = "last_update_date")
    var lastUpdateDate: LocalDateTime = LocalDateTime.now()
}