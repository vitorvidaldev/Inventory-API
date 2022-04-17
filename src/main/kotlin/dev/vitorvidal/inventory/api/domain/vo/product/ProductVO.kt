package dev.vitorvidal.inventory.api.domain.vo.product

import java.time.LocalDateTime
import java.util.*

data class ProductVO(
    val id: UUID,
    val productName: String,
    val productPrice: String,
    val creationDate: LocalDateTime,
    val lastUpdateDate: LocalDateTime,
    val stock: Int
)