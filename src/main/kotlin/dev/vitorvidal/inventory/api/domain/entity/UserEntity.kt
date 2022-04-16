package dev.vitorvidal.inventory.api.domain.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"user\"")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var userId: UUID,
    @Column(unique = true)
    var email: String,
    var hashedPassword: String,
    var creationDate: LocalDateTime
)