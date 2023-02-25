package dev.vitorvidal.inventory.api.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "\"user\"") // the quotes are used to avoid a database conflict
class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val userId: UUID? = null

    @Column(name = "email", unique = true)
    var email: String? = null

    @Column(name = "password")
    var password: String? = null

    @Column(name = "creation_date")
    var creationDate: LocalDateTime? = null

    @Column(name = "last_update_date")
    var lastUpdateDate: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        return true
    }

    override fun hashCode(): Int = userId?.hashCode() ?: 0

    override fun toString(): String {
        return "User(userId=$userId, email=$email, password=$password, creationDate=$creationDate, lastUpdateDate=$lastUpdateDate)"
    }


}