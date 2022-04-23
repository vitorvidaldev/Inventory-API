package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.entity.ProductEntity
import dev.vitorvidal.inventory.api.domain.repository.ProductRepository
import dev.vitorvidal.inventory.api.domain.vo.product.ProductVO
import dev.vitorvidal.inventory.api.domain.vo.product.RegisterProductVO
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
        val productEntity = ProductEntity(
            registerProductVO.productName,
            registerProductVO.productBrand,
            registerProductVO.productPrice
        )

        val createdProduct = productRepository.save(productEntity)

        return ProductVO(
            createdProduct.productId,
            createdProduct.productName,
            createdProduct.productPrice,
            createdProduct.creationDate,
            createdProduct.lastUpdateDate
        )
    }

    fun removeProduct(productId: UUID) {
        TODO("Not yet implemented")
    }
}