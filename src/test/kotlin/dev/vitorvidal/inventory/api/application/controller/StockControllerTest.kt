package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.impl.StockServiceImpl
import dev.vitorvidal.inventory.api.domain.vo.stock.SetStockVO
import dev.vitorvidal.inventory.api.domain.vo.stock.StockVO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
internal class StockControllerTest {
    @InjectMocks
    lateinit var stockController: StockController

    @Mock
    lateinit var stockServiceImpl: StockServiceImpl

    @Test
    fun shouldGetCurrentProductStockCorrectly() {
        val productIdMock: UUID = UUID.randomUUID()
        val stockVOMock: StockVO = mock(StockVO::class.java)

        `when`(stockServiceImpl.getProductStock(productIdMock)).thenReturn(stockVOMock)

        val response = stockController.getCurrentProductStock(productIdMock)

        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(stockVOMock, response.body)

        verify(stockServiceImpl).getProductStock(productIdMock)
    }

    @Test
    fun shouldUpdateProductStockCorrectly() {
        val setStockVOMock: SetStockVO = mock(SetStockVO::class.java)
        val stockVOMock: StockVO = mock(StockVO::class.java)

        `when`(stockServiceImpl.setProductStock(setStockVOMock)).thenReturn(stockVOMock)

        val response = stockController.updateProductStock(setStockVOMock)

        assertNotNull(response)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(stockVOMock, response.body)

        verify(stockServiceImpl).setProductStock(setStockVOMock)
    }
}