package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.impl.SaleServiceImpl
import dev.vitorvidal.inventory.api.domain.vo.sale.SaleVO
import dev.vitorvidal.inventory.api.domain.vo.sale.SellVO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import org.springframework.data.domain.PageImpl
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
internal class SaleControllerTest {
    @InjectMocks
    lateinit var saleController: SaleController

    @Mock
    lateinit var saleServiceImpl: SaleServiceImpl

    @Test
    fun shouldGetSaleDataCorrectly() {
        val saleIdMock = UUID.randomUUID()
        val saleVOMock = mock(SaleVO::class.java)

        `when`(saleServiceImpl.getSaleData(saleIdMock)).thenReturn(saleVOMock)

        val response = saleController.getSaleData(saleIdMock)

        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(saleVOMock, response.body)

        verify(saleServiceImpl).getSaleData(saleIdMock)
    }

    @Test
    fun shouldGetPurchaseHistoryCorrectly() {
        val userIdMock = UUID.randomUUID()
        val saleVOMock = mock(SaleVO::class.java)

        `when`(saleServiceImpl.getUserPurchaseHistory(userIdMock))
            .thenReturn(PageImpl(listOf(saleVOMock)))

        val response = saleController.getUserPurchaseHistory(userIdMock)

        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertEquals(listOf(saleVOMock), response.body?.content)

        verify(saleServiceImpl).getUserPurchaseHistory(userIdMock)
    }

    @Test
    fun shouldSellCorrectly() {
        val sellVOMock = mock(SellVO::class.java)
        val saleVOMock = mock(SaleVO::class.java)

        `when`(saleServiceImpl.sell(sellVOMock)).thenReturn(saleVOMock)

        val response = saleController.sell(sellVOMock)

        assertNotNull(response)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(saleVOMock, response.body)

        verify(saleServiceImpl).sell(sellVOMock)
    }
}