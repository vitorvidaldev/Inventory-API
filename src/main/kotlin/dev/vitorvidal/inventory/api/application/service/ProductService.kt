package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.repository.ProductRepository
import dev.vitorvidal.inventory.api.domain.vo.product.ProductVO
import dev.vitorvidal.inventory.api.domain.vo.product.RegisterProductVO
import dev.vitorvidal.inventory.api.domain.vo.product.UpdateProductVO
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(val productRepository: ProductRepository) {
    fun getProductList(): List<ProductVO> {
        TODO("Not yet implemented")
    }

    fun getProductById(productId: UUID): ProductVO {
        TODO("Not yet implemented")
    }

    fun registerProduct(registerProductVO: RegisterProductVO): ProductVO {
        TODO("Not yet implemented")
    }

    fun updateStock(productId: UUID, updateProductVO: UpdateProductVO): ProductVO {
        TODO("Not yet implemented")
    }
}