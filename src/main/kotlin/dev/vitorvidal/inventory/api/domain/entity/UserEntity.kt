package dev.vitorvidal.inventory.api.domain.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"user\"")
class UserEntity() {
    constructor(email: String, password: String) : this() {
        this.email = email
        this.hashedPassword = password
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val userId: UUID = UUID.randomUUID()

    @Column(name = "email", unique = true)
    var email: String = ""

    @Column(name = "password")
    var hashedPassword: String = ""

    @Column(name = "creationDate")
    val creationDate: LocalDateTime = LocalDateTime.now()
}