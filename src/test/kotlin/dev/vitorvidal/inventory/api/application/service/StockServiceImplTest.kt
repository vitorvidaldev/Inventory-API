package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.application.service.impl.StockServiceImpl
import dev.vitorvidal.inventory.api.domain.entity.Product
import dev.vitorvidal.inventory.api.domain.entity.Stock
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
internal class StockServiceImplTest {
    @InjectMocks
    lateinit var stockServiceImpl: StockServiceImpl

    @Mock
    lateinit var stockRepository: StockRepository

    @Test
    fun shouldGetProductStockCorrectly() {
        val productIdMock: UUID = UUID.randomUUID()
        val productMock = mock(Product::class.java)
        val stockMock = mock(Stock::class.java)
        val stockValue = 10
        val stockId = UUID.randomUUID()

        `when`(stockRepository.findByProduct(productMock)).thenReturn(Optional.of(stockMock))
        `when`(stockMock.stockId).thenReturn(stockId)
        `when`(stockMock.product).thenReturn(productMock)
        `when`(stockMock.value).thenReturn(stockValue)

        val stockVO = stockServiceImpl.getProductStock(productIdMock)

        assertNotNull(stockVO)
        assertEquals(stockId, stockVO.stockId)
        assertEquals(productIdMock, stockVO.productId)
        assertEquals(stockValue, stockVO.value)

        verify(stockRepository).findByProduct(productMock)
    }

    @Test
    fun shouldThrowNotFoundExceptionGettingStock() {
        val productIdMock: UUID = UUID.randomUUID()
        val productMock = mock(Product::class.java)


        `when`(stockRepository.findByProduct(productMock)).thenReturn(Optional.empty())

        val exception = assertThrows(ResponseStatusException::class.java) {
            stockServiceImpl.getProductStock(productIdMock)
        }

        assertNotNull(exception)
        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode)
        assertEquals("Stock not found for product $productIdMock", exception.reason)


        verify(stockRepository).findByProduct(productMock)
    }

    @Test
    fun shouldUpdateProductStockCorrectly() {
        val setStockVOMock = mock(SetStockVO::class.java)
        val productMock = mock(Product::class.java)
        val productIdMock = UUID.randomUUID()
        val stockIdMock = UUID.randomUUID()
        val stockValueMock = 10
        val stockMock = mock(Stock::class.java)

        `when`(setStockVOMock.value).thenReturn(stockValueMock)
        `when`(setStockVOMock.stockId).thenReturn(stockIdMock)
        `when`(setStockVOMock.productId).thenReturn(productIdMock)

        `when`(stockRepository.findById(stockIdMock)).thenReturn(Optional.of(stockMock))

        `when`(stockMock.value).thenReturn(stockValueMock)
        `when`(stockMock.stockId).thenReturn(stockIdMock)
        `when`(stockMock.product).thenReturn(productMock)

        val stockVO = stockServiceImpl.setProductStock(setStockVOMock)

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
        val stockMock = mock(Stock::class.java)
        val productMock = mock(Product::class.java)

        `when`(setStockVOMock.value).thenReturn(stockValueMock)
        `when`(setStockVOMock.productId).thenReturn(productIdMock)

        `when`(stockRepository.save(any(Stock::class.java))).thenReturn(stockMock)

        `when`(stockMock.value).thenReturn(stockValueMock)
        `when`(stockMock.stockId).thenReturn(stockIdMock)
        `when`(stockMock.product).thenReturn(productMock)

        val stockVO = stockServiceImpl.setProductStock(setStockVOMock)

        assertNotNull(stockVO)
        assertEquals(stockValueMock, stockVO.value)
        assertEquals(productIdMock, stockVO.productId)

        verify(stockRepository, times(0)).findById(stockIdMock)
    }
}