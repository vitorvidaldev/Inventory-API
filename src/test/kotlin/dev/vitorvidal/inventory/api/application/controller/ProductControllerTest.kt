package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.ProductService
import dev.vitorvidal.inventory.api.domain.vo.product.ProductVO
import dev.vitorvidal.inventory.api.domain.vo.product.RegisterProductVO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.data.domain.PageImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
internal class ProductControllerTest {
    @InjectMocks
    lateinit var productController: ProductController

    @Mock
    lateinit var productService: ProductService

    @Test
    fun shouldGetProductListCorrectly() {
        val productVOMock: ProductVO = mock(ProductVO::class.java)
        val productNameMock = "product name"
        val productBrandMock = "brand"
        val pageNumberMock = 0

        `when`(productService.getProducts(productNameMock, productBrandMock, pageNumberMock)).thenReturn(PageImpl(listOf(productVOMock)))

        val response = productController.getProducts(productNameMock, productBrandMock, pageNumberMock)

        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(listOf(productVOMock), response.body?.content)

        verify(productService).getProducts(productNameMock, productBrandMock, pageNumberMock)
    }

    @Test
    fun shouldGetProductByIdCorrectly() {
        val productIdMock = UUID.randomUUID()
        val productVOMock: ProductVO = mock(ProductVO::class.java)

        `when`(productService.getProductById(productIdMock)).thenReturn(productVOMock)

        val response = productController.getProductById(productIdMock)

        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(productVOMock, response.body)

        verify(productService).getProductById(productIdMock)
    }

    @Test
    fun shouldRegisterProductCorrectly() {
        val registerProductVOMock: RegisterProductVO = mock(RegisterProductVO::class.java)
        val productVOMock: ProductVO = mock(ProductVO::class.java)

        `when`(productService.registerProduct(registerProductVOMock)).thenReturn(productVOMock)

        val response = productController.registerProduct(registerProductVOMock)

        assertNotNull(response)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(productVOMock, response.body)

        verify(productService).registerProduct(registerProductVOMock)
    }

    @Test
    fun shouldRemoveProductCorrectly() {
        val productIdMock: UUID = UUID.randomUUID()

        `when`(productService.removeProduct(productIdMock))
            .thenReturn(ResponseEntity.noContent().build())

        val response = productController.removeProduct(productIdMock)

        assertNotNull(response)
        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
        assertNull(response.body)

        verify(productService).removeProduct(productIdMock)
    }
}