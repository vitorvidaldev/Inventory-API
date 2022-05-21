package dev.vitorvidal.inventory.api.domain.vo.product

import java.time.LocalDateTime
import java.util.*

data class ProductVO(
    val id: UUID,
    val productName: String,
    val productBrand: String,
    val productPrice: Double,
    val creationDate: LocalDateTime,
    val isActive: Boolean,
    val lastUpdateDate: LocalDateTime,
    val userId: UUID
)