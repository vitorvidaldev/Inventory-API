package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.vo.product.ProductVO
import dev.vitorvidal.inventory.api.domain.vo.product.RegisterProductVO
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import java.util.*

interface ProductService {
    fun getProducts(productName: String?, productBrand: String?, pageNumber: Int): Page<ProductVO>
    fun getProductById(productId: UUID): ProductVO
    fun registerProduct(registerProductVO: RegisterProductVO): ProductVO
    fun removeProduct(productId: UUID): ResponseEntity<Void>
}