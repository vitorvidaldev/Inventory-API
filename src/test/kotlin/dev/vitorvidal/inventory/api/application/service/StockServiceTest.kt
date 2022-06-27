package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.entity.StockEntity
import dev.vitorvidal.inventory.api.domain.repository.StockRepository
import dev.vitorvidal.inventory.api.domain.vo.stock.SetStockVO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.kotlin.verify
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.server.ResponseStatusException
import java.util.*

@ExtendWith(SpringExtension::class)
internal class StockServiceTest {
    @InjectMocks
    lateinit var stockService: StockService

    @Mock
    lateinit var stockRepository: StockRepository

    @Test
    fun shouldGetProductStockCorrectly() {
        val productIdMock: UUID = UUID.randomUUID()
        val stockEntityMock = mock(StockEntity::class.java)
        val stockValue = 10
        val stockId = UUID.randomUUID()

        `when`(stockRepository.findByProductId(productIdMock)).thenReturn(Optional.of(stockEntityMock))
        `when`(stockEntityMock.stockId).thenReturn(stockId)
        `when`(stockEntityMock.productId).thenReturn(productIdMock)
        `when`(stockEntityMock.value).thenReturn(stockValue)

        val stockVO = stockService.getProductStock(productIdMock)

        assertNotNull(stockVO)
        assertEquals(stockId, stockVO.stockId)
        assertEquals(productIdMock, stockVO.productId)
        assertEquals(stockValue, stockVO.value)

        verify(stockRepository).findByProductId(productIdMock)
    }

    @Test
    fun shouldThrowNotFoundExceptionGettingStock() {
        val productIdMock: UUID = UUID.randomUUID()

        `when`(stockRepository.findByProductId(productIdMock)).thenReturn(Optional.empty())

        val exception = assertThrows(ResponseStatusException::class.java) {
            stockService.getProductStock(productIdMock)
        }

        assertNotNull(exception)
        assertEquals(HttpStatus.NOT_FOUND, exception.status)
        assertEquals("Stock not found for product $productIdMock", exception.reason)


        verify(stockRepository).findByProductId(productIdMock)
    }

    @Test
    fun shouldUpdateProductStockCorrectly() {
        val setStockVOMock = mock(SetStockVO::class.java)
        val productIdMock = UUID.randomUUID()
        val stockIdMock = UUID.randomUUID()
        val stockValueMock = 10
        val stockEntityMock = mock(StockEntity::class.java)

        `when`(setStockVOMock.value).thenReturn(stockValueMock)
        `when`(setStockVOMock.stockId).thenReturn(stockIdMock)
        `when`(setStockVOMock.productId).thenReturn(productIdMock)

        `when`(stockRepository.findById(stockIdMock)).thenReturn(Optional.of(stockEntityMock))

        `when`(stockEntityMock.value).thenReturn(stockValueMock)
        `when`(stockEntityMock.stockId).thenReturn(stockIdMock)
        `when`(stockEntityMock.productId).thenReturn(productIdMock)

        val stockVO = stockService.setProductStock(setStockVOMock)

        assertNotNull(stockVO)
        assertEquals(stockValueMock, stockVO.value)
        assertEquals(stockIdMock, stockVO.stockId)
        assertEquals(productIdMock, stockVO.productId)

        verify(stockRepository).findById(stockIdMock)
    }

    @Test
    fun shouldCreateProductStockCorrectly() {
        val setStockVOMock = mock(SetStockVO::class.java)
        val productIdMock = UUID.randomUUID()
        val stockIdMock = UUID.randomUUID()
        val stockValueMock = 10
        val stockEntityMock = mock(StockEntity::class.java)

        `when`(setStockVOMock.value).thenReturn(stockValueMock)
        `when`(setStockVOMock.productId).thenReturn(productIdMock)

        `when`(stockRepository.save(any(StockEntity::class.java))).thenReturn(stockEntityMock)

        `when`(stockEntityMock.value).thenReturn(stockValueMock)
        `when`(stockEntityMock.stockId).thenReturn(stockIdMock)
        `when`(stockEntityMock.productId).thenReturn(productIdMock)

        val stockVO = stockService.setProductStock(setStockVOMock)

        assertNotNull(stockVO)
        assertEquals(stockValueMock, stockVO.value)
        assertEquals(productIdMock, stockVO.productId)

        verify(stockRepository, times(0)).findById(stockIdMock)
    }
}