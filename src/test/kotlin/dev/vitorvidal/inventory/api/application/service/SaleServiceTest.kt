package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.entity.SaleEntity
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
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.server.ResponseStatusException
import java.util.*

@ExtendWith(SpringExtension::class)
internal class SaleServiceTest {
    @InjectMocks
    lateinit var saleService: SaleService

    @Mock
    lateinit var saleRepository: SaleRepository

    @Test
    fun shouldGetSaleCorrectly() {
        val saleIdMock = UUID.randomUUID()
        val userIdMock = UUID.randomUUID()
        val productIdMock = UUID.randomUUID()
        val saleEntityMock = mock(SaleEntity::class.java)

        `when`(saleRepository.findById(saleIdMock)).thenReturn(Optional.of(saleEntityMock))

        `when`(saleEntityMock.saleId).thenReturn(saleIdMock)
        `when`(saleEntityMock.buyerId).thenReturn(userIdMock)
        `when`(saleEntityMock.productId).thenReturn(productIdMock)

        val saleVO = saleService.getSaleData(saleIdMock)

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
            saleService.getSaleData(saleIdMock)
        }

        assertNotNull(exception)
        assertEquals(HttpStatus.NOT_FOUND, exception.status)
        assertEquals("Error getting sale data", exception.reason)

        verify(saleRepository).findById(saleIdMock)
    }

    @Test
    fun shouldGetPurchaseHistoryCorrectly() {
        val userIdMock = UUID.randomUUID()
        val saleIdMock = UUID.randomUUID()
        val productIdMock = UUID.randomUUID()
        val saleEntityMock = mock(SaleEntity::class.java)

        `when`(saleRepository.findByBuyerId(userIdMock)).thenReturn(PageImpl(listOf(saleEntityMock)))

        `when`(saleEntityMock.saleId).thenReturn(saleIdMock)
        `when`(saleEntityMock.buyerId).thenReturn(userIdMock)
        `when`(saleEntityMock.productId).thenReturn(productIdMock)

        val purchaseHistory = saleService.getUserPurchaseHistory(userIdMock)

        assertNotNull(purchaseHistory)
        assertEquals(saleIdMock, purchaseHistory.content[0].saleId)
        assertEquals(userIdMock, purchaseHistory.content[0].buyerId)
        assertEquals(productIdMock, purchaseHistory.content[0].productId)

        verify(saleRepository).findByBuyerId(userIdMock)
    }

    @Test
    fun shouldSellCorrectly() {
        val userIdMock = UUID.randomUUID()
        val saleIdMock = UUID.randomUUID()
        val productIdMock = UUID.randomUUID()
        val saleEntityMock = mock(SaleEntity::class.java)
        val sellVOMock = mock(SellVO::class.java)

        `when`(saleEntityMock.saleId).thenReturn(saleIdMock)
        `when`(saleEntityMock.buyerId).thenReturn(userIdMock)
        `when`(saleEntityMock.productId).thenReturn(productIdMock)

        `when`(sellVOMock.productId).thenReturn(productIdMock)
        `when`(sellVOMock.userId).thenReturn(userIdMock)

        `when`(saleRepository.save(any())).thenReturn(saleEntityMock)

        val saleVO = saleService.sell(sellVOMock)

        assertNotNull(saleVO)
        assertEquals(saleIdMock, saleVO.saleId)
        assertEquals(userIdMock, saleVO.buyerId)
        assertEquals(productIdMock, saleVO.productId)

        verify(saleRepository).save(any())
    }
}