package dev.vitorvidal.inventory.api.domain.vo.product

data class RegisterProductVO(
    val productName: String,
    val productBrand: String,
    val productPrice: Long
)