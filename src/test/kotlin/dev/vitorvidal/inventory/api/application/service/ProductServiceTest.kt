package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.entity.Product
import dev.vitorvidal.inventory.api.domain.repository.ProductRepository
import dev.vitorvidal.inventory.api.domain.vo.product.RegisterProductVO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.kotlin.any
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.PageImpl
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.util.*

@ExtendWith(SpringExtension::class)
internal class ProductServiceTest {
    @InjectMocks
    lateinit var productService: ProductService

    @Mock
    lateinit var productRepository: ProductRepository

    @Test
    fun shouldGetProductListCorrectly() {
        val productMock: Product = mock(Product::class.java)

        val productIdMock = UUID.randomUUID()
        val userIdMock = UUID.randomUUID()
        val productNameMock = "product name"
        val productBrandMock = "product brand"
        val productPriceMock = 10.0
        val creationDateMock = LocalDateTime.now()
        val lastUpdateDateMock = LocalDateTime.now()

        `when`(productRepository.findAll()).thenReturn(listOf(productMock))
        `when`(productMock.productId).thenReturn(productIdMock)
        `when`(productMock.productName).thenReturn(productNameMock)
        `when`(productMock.productBrand).thenReturn(productBrandMock)
        `when`(productMock.productPrice).thenReturn(productPriceMock)
        `when`(productMock.creationDate).thenReturn(creationDateMock)
        `when`(productMock.lastUpdateDate).thenReturn(lastUpdateDateMock)
        `when`(productMock.userId).thenReturn(userIdMock)

        `when`(
            productRepository.findByFilter(
                eq(productNameMock),
                eq(productBrandMock),
                any()
            )
        ).thenReturn(PageImpl(listOf(productMock)))

        val productList = productService.getProducts(productNameMock, productBrandMock, 0)

        assertNotNull(productList)

        verify(productRepository).findByFilter(
            eq(productNameMock),
            eq(productBrandMock),
            any()
        )
    }

    @Test
    fun shouldGetProductProductByIdCorrectly() {
        val productMock = mock(Product::class.java)

        val productIdMock = UUID.randomUUID()
        val userIdMock = UUID.randomUUID()
        val productNameMock = "product name"
        val productBrandMock = "product brand"
        val productPriceMock = 10.0
        val creationDateMock = LocalDateTime.now()
        val lastUpdateDateMock = LocalDateTime.now()

        `when`(productRepository.findById(productIdMock)).thenReturn(Optional.of(productMock))

        `when`(productMock.productId).thenReturn(productIdMock)
        `when`(productMock.productName).thenReturn(productNameMock)
        `when`(productMock.productBrand).thenReturn(productBrandMock)
        `when`(productMock.productPrice).thenReturn(productPriceMock)
        `when`(productMock.creationDate).thenReturn(creationDateMock)
        `when`(productMock.lastUpdateDate).thenReturn(lastUpdateDateMock)
        `when`(productMock.userId).thenReturn(userIdMock)

        val productById = productService.getProductById(productIdMock)

        assertNotNull(productById)
        assertEquals(productIdMock, productById.id)
        assertEquals(productNameMock, productById.productName)
        assertEquals(productBrandMock, productById.productBrand)
        assertEquals(productPriceMock, productById.productPrice)
        assertEquals(creationDateMock, productById.creationDate)
        assertEquals(lastUpdateDateMock, productById.lastUpdateDate)

        verify(productRepository).findById(productIdMock)
    }

    @Test
    fun shouldThrowNotFoundExceptionGettingProductById() {
        val productIdMock = UUID.randomUUID()

        `when`(productRepository.findById(productIdMock)).thenReturn(Optional.empty())

        val exception = assertThrows(ResponseStatusException::class.java) {
            productService.getProductById(productIdMock)
        }

        assertNotNull(exception)
        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode)
        assertEquals("Product not found", exception.reason)

        verify(productRepository).findById(productIdMock)
    }

    @Test
    fun shouldRegisterProductCorrectly() {
        val registerProductVOMock: RegisterProductVO = mock(RegisterProductVO::class.java)
        val productMock: Product = mock(Product::class.java)

        val productIdMock = UUID.randomUUID()
        val userIdMock = UUID.randomUUID()
        val productNameMock = "product name"
        val productBrandMock = "brand"
        val productPriceMock = 10.0
        val creationDateMock = LocalDateTime.now()
        val lastUpdateDateMock = LocalDateTime.now()

        `when`(productRepository.save(any(Product::class.java))).thenReturn(productMock)

        `when`(registerProductVOMock.productName).thenReturn(productNameMock)
        `when`(registerProductVOMock.productBrand).thenReturn(productBrandMock)
        `when`(registerProductVOMock.productPrice).thenReturn(productPriceMock)
        `when`(registerProductVOMock.userId).thenReturn(userIdMock)

        `when`(productMock.productId).thenReturn(productIdMock)
        `when`(productMock.userId).thenReturn(userIdMock)
        `when`(productMock.productName).thenReturn(productNameMock)
        `when`(productMock.productBrand).thenReturn(productBrandMock)
        `when`(productMock.productPrice).thenReturn(productPriceMock)
        `when`(productMock.creationDate).thenReturn(creationDateMock)
        `when`(productMock.lastUpdateDate).thenReturn(lastUpdateDateMock)

        val registerProduct = productService.registerProduct(registerProductVOMock)

        assertNotNull(registerProduct)
        assertEquals(productIdMock, registerProduct.id)
        assertEquals(productNameMock, registerProduct.productName)
        assertEquals(productBrandMock, registerProduct.productBrand)
        assertEquals(productPriceMock, registerProduct.productPrice)
        assertEquals(creationDateMock, registerProduct.creationDate)
        assertEquals(lastUpdateDateMock, registerProduct.lastUpdateDate)
        assertEquals(userIdMock, registerProduct.userId)

        verify(productRepository).save(any(Product::class.java))
    }

    @Test
    fun shouldRemoveProductCorrectly() {
        val productIdMock = UUID.randomUUID()

        doNothing().`when`(productRepository).deleteById(productIdMock)

        val removeProduct = productService.removeProduct(productIdMock)

        assertNotNull(removeProduct)
        assertEquals(HttpStatus.NO_CONTENT, removeProduct.statusCode)
        assertNull(removeProduct.body)

        verify(productRepository).deleteById(productIdMock)
    }

    @Test
    fun shouldRemoveProductMultipleTimesCorrectly() {
        val productIdMock = UUID.randomUUID()

        doThrow(EmptyResultDataAccessException::class.java).`when`(productRepository).deleteById(productIdMock)

        val removeProduct = productService.removeProduct(productIdMock)

        assertNotNull(removeProduct)
        assertEquals(HttpStatus.NOT_FOUND, removeProduct.statusCode)
        assertNull(removeProduct.body)

        verify(productRepository).deleteById(productIdMock)
    }
}