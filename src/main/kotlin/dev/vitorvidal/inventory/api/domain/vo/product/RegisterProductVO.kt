package dev.vitorvidal.inventory.api.domain.vo.product

import java.util.*

data class RegisterProductVO(
    val userId: UUID,
    val productName: String,
    val productBrand: String,
    val productPrice: Double
)