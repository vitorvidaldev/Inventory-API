package dev.vitorvidal.inventory.api.domain.entity

import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Data
@NoArgsConstructor
@Entity
@Table(name = "\"user\"")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var userId: UUID,
    @Column(unique = true)
    var email: String,
    var hashedPassword: String,
    var creationDate: LocalDateTime
) {
    constructor(email: String, password: String) : this(UUID.randomUUID(), email, password, LocalDateTime.now())
}