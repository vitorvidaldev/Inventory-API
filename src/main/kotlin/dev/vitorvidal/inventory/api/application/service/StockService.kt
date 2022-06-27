package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.entity.StockEntity
import dev.vitorvidal.inventory.api.domain.repository.StockRepository
import dev.vitorvidal.inventory.api.domain.vo.stock.SetStockVO
import dev.vitorvidal.inventory.api.domain.vo.stock.StockVO
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class StockService(val stockRepository: StockRepository) {
    fun getProductStock(productId: UUID): StockVO {
        val optionalStock: Optional<StockEntity> = stockRepository.findByProductId(productId)

        if (optionalStock.isPresent) {
            val stockEntity = optionalStock.get()

            return StockVO(
                stockEntity.stockId,
                stockEntity.productId,
                stockEntity.value
            )
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found for product $productId")
    }

    fun setProductStock(setStockVO: SetStockVO): StockVO {
        if (setStockVO.stockId != null) {
            val optionalStock = stockRepository.findById(setStockVO.stockId)
            if (optionalStock.isPresent) {
                val stockEntity = optionalStock.get()
                stockEntity.value = setStockVO.value
                stockRepository.save(stockEntity)
                return StockVO(stockEntity.stockId, stockEntity.productId, stockEntity.value)
            }
        }
        val stockEntity = StockEntity(setStockVO.value, setStockVO.productId)
        stockRepository.save(stockEntity)
        return StockVO(stockEntity.stockId, stockEntity.productId, stockEntity.value)
    }
}