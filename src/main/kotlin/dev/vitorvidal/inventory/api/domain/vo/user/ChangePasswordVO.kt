package dev.vitorvidal.inventory.api.domain.vo.user

import java.util.*

data class ChangePasswordVO(
    val userId: UUID,
    val email: String,
    val oldPassword: String,
    val newPassword: String
)