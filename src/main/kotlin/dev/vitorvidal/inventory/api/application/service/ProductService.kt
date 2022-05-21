package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.entity.ProductEntity
import dev.vitorvidal.inventory.api.domain.repository.ProductRepository
import dev.vitorvidal.inventory.api.domain.vo.product.ProductVO
import dev.vitorvidal.inventory.api.domain.vo.product.RegisterProductVO
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Slf4j
@Service
class ProductService(val productRepository: ProductRepository) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    fun getProducts(productName: String?, productBrand: String?, pageNumber: Int): Page<ProductVO> {
        val page: Pageable = PageRequest.of(pageNumber, 10, Sort.by("product_name"))

        val productPage = productRepository.getFilter(productName, productBrand, page)

        return productPage.map { productEntity ->
            ProductVO(
                productEntity.productId,
                productEntity.productName,
                productEntity.productBrand,
                productEntity.productPrice,
                productEntity.creationDate,
                productEntity.isActive,
                productEntity.lastUpdateDate
            )
        }
    }

    fun getProductById(productId: UUID): ProductVO {
        val optionalProduct = productRepository.findById(productId)

        if (optionalProduct.isPresent) {
            val productEntity = optionalProduct.get()

            return ProductVO(
                productEntity.productId,
                productEntity.productName,
                productEntity.productBrand,
                productEntity.productPrice,
                productEntity.creationDate,
                productEntity.isActive,
                productEntity.lastUpdateDate
            )
        }

        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
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
            createdProduct.productBrand,
            createdProduct.productPrice,
            createdProduct.creationDate,
            productEntity.isActive,
            createdProduct.lastUpdateDate
        )
    }

    fun removeProduct(productId: UUID): ResponseEntity<Void> {
        return try {
            productRepository.deleteById(productId)
            ResponseEntity.noContent().build()
        } catch (e: Exception) {
            log.error("Did not find product to delete")
            ResponseEntity.notFound().build()
        }
    }
}