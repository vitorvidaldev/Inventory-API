package dev.vitorvidal.inventory.api.domain.vo.user

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Column
import java.time.LocalDateTime
import java.util.*

@Schema(name = "User", description = "User data")
data class UserVO(
    var userId: UUID = UUID.randomUUID(),
    @Column(unique = true)
    var email: String,
    var creationDate: LocalDateTime = LocalDateTime.now()
)