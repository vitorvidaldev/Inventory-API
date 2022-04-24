package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.StockService
import dev.vitorvidal.inventory.api.domain.vo.stock.StockVO
import dev.vitorvidal.inventory.api.domain.vo.stock.UpdateStockVO
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
    lateinit var stockService: StockService

    @Test
    fun shouldGetCurrentProductStockCorrectly() {
        val productIdMock: UUID = UUID.randomUUID()
        val stockVOMock: StockVO = mock(StockVO::class.java)

        `when`(stockService.getCurrentProductStock(productIdMock)).thenReturn(stockVOMock)

        val response = stockController.getCurrentProductStock(productIdMock)

        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(stockVOMock, response.body)

        verify(stockService).getCurrentProductStock(productIdMock)
    }

    @Test
    fun shouldUpdateProductStockCorrectly() {
        val updateStockVOMock: UpdateStockVO = mock(UpdateStockVO::class.java)
        val stockVOMock: StockVO = mock(StockVO::class.java)

        `when`(stockService.updateProductStock(updateStockVOMock)).thenReturn(stockVOMock)

        val response = stockController.updateProductStock(updateStockVOMock)

        assertNotNull(response)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(stockVOMock, response.body)

        verify(stockService).updateProductStock(updateStockVOMock)
    }
}