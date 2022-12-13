package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.application.service.impl.SaleServiceImpl
import dev.vitorvidal.inventory.api.domain.entity.Sale
import dev.vitorvidal.inventory.api.domain.repository.SaleRepository
import dev.vitorvidal.inventory.api.domain.vo.sale.SellVO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.server.ResponseStatusException
import java.util.*

@ExtendWith(SpringExtension::class)
internal class SaleServiceImplTest {
    @InjectMocks
    lateinit var saleServiceImpl: SaleServiceImpl

    @Mock
    lateinit var saleRepository: SaleRepository

    @Test
    fun shouldGetSaleCorrectly() {
        val saleIdMock = UUID.randomUUID()
        val userIdMock = UUID.randomUUID()
        val productIdMock = UUID.randomUUID()
        val saleMock = mock(Sale::class.java)

        `when`(saleRepository.findById(saleIdMock)).thenReturn(Optional.of(saleMock))

        `when`(saleMock.saleId).thenReturn(saleIdMock)
        `when`(saleMock.buyerId).thenReturn(userIdMock)
        `when`(saleMock.productId).thenReturn(productIdMock)

        val saleVO = saleServiceImpl.getSaleData(saleIdMock)

        assertNotNull(saleVO)
        assertEquals(saleIdMock, saleVO.saleId)
        assertEquals(userIdMock, saleVO.buyerId)
        assertEquals(productIdMock, saleVO.productId)

        verify(saleRepository).findById(saleIdMock)
    }

    @Test
    fun shouldThrowNotFoundExceptionGettingSaleData() {
        val saleIdMock = UUID.randomUUID()

        `when`(saleRepository.findById(saleIdMock)).thenReturn(Optional.empty())

        val exception = assertThrows(ResponseStatusException::class.java) {
            saleServiceImpl.getSaleData(saleIdMock)
        }

        assertNotNull(exception)
        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode)
        assertEquals("Error getting sale data", exception.reason)

        verify(saleRepository).findById(saleIdMock)
    }

    @Test
    fun shouldGetPurchaseHistoryCorrectly() {
        val userIdMock = UUID.randomUUID()
        val saleIdMock = UUID.randomUUID()
        val productIdMock = UUID.randomUUID()
        val saleMock = mock(Sale::class.java)

        `when`(saleRepository.findByBuyerId(userIdMock, Pageable.ofSize(10))).thenReturn(PageImpl(listOf(saleMock)))

        `when`(saleMock.saleId).thenReturn(saleIdMock)
        `when`(saleMock.buyerId).thenReturn(userIdMock)
        `when`(saleMock.productId).thenReturn(productIdMock)

        val purchaseHistory = saleServiceImpl.getUserPurchaseHistory(userIdMock)

        assertNotNull(purchaseHistory)
        assertEquals(saleIdMock, purchaseHistory.content[0].saleId)
        assertEquals(userIdMock, purchaseHistory.content[0].buyerId)
        assertEquals(productIdMock, purchaseHistory.content[0].productId)

        verify(saleRepository).findByBuyerId(userIdMock, Pageable.ofSize(10))
    }

    @Test
    fun shouldSellCorrectly() {
        val userIdMock = UUID.randomUUID()
        val saleIdMock = UUID.randomUUID()
        val productIdMock = UUID.randomUUID()
        val saleMock = mock(Sale::class.java)
        val sellVOMock = mock(SellVO::class.java)

        `when`(saleMock.saleId).thenReturn(saleIdMock)
        `when`(saleMock.buyerId).thenReturn(userIdMock)
        `when`(saleMock.productId).thenReturn(productIdMock)

        `when`(sellVOMock.productId).thenReturn(productIdMock)
        `when`(sellVOMock.userId).thenReturn(userIdMock)

        `when`(saleRepository.save(any())).thenReturn(saleMock)

        val saleVO = saleServiceImpl.sell(sellVOMock)

        assertNotNull(saleVO)
        assertEquals(saleIdMock, saleVO.saleId)
        assertEquals(userIdMock, saleVO.buyerId)
        assertEquals(productIdMock, saleVO.productId)

        verify(saleRepository).save(any())
    }
}