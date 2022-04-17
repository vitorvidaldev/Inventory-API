package dev.vitorvidal.inventory.api.domain.vo.product

import java.util.*

data class UpdateProductVO(
    val id: UUID,
    val productName: String?,
    val productPrice: String?
)