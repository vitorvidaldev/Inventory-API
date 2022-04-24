package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.repository.StockRepository
import dev.vitorvidal.inventory.api.domain.vo.stock.StockVO
import org.springframework.stereotype.Service
import java.util.*

@Service
class StockService(stockRepository: StockRepository) {
    fun getCurrentProductStock(productId: UUID): StockVO {
        TODO("Not yet implemented")
    }

    fun updateProductStock(addStockVO: Any): StockVO {
        TODO("Not yet implemented")
    }
}