package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.entity.StockEntity
import dev.vitorvidal.inventory.api.domain.repository.ProductRepository
import dev.vitorvidal.inventory.api.domain.repository.StockRepository
import dev.vitorvidal.inventory.api.domain.vo.stock.SetStockVO
import dev.vitorvidal.inventory.api.domain.vo.stock.StockVO
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class StockService(val stockRepository: StockRepository, val productRepository: ProductRepository) {
    fun getCurrentProductStock(productId: UUID): StockVO {
        val optionalProduct = productRepository.findById(productId)

        if (optionalProduct.isPresent) {
            val productEntity = optionalProduct.get()

            val optionalStock: Optional<StockEntity> = stockRepository.findByProduct(productEntity)

            if (optionalStock.isPresent) {
                val stockEntity = optionalStock.get()

                return StockVO(
                    stockEntity.stockId,
                    stockEntity.product.productId,
                    stockEntity.stock
                )
            }
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product stock not found")
    }

    fun setProductStock(setStockVO: SetStockVO): StockVO {
        if (setStockVO.stockId != null) {
            val optionalStock = stockRepository.findById(setStockVO.stockId)
            if (optionalStock.isPresent) {
                return updateStock(setStockVO, optionalStock.get())
            }
        }
        return setNewStock(setStockVO)
    }

    fun updateStock(setStockVO: SetStockVO, stockEntity: StockEntity): StockVO {
        if (Objects.equals(stockEntity.product.productId, setStockVO.productId)) {
            stockEntity.stock = setStockVO.stock
            stockRepository.save(stockEntity)

            return StockVO(stockEntity.stockId, stockEntity.product.productId, stockEntity.stock)
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product stock not found")
    }

    fun setNewStock(setStockVO: SetStockVO): StockVO {
        val optionalProduct = productRepository.findById(setStockVO.productId)
        if (optionalProduct.isPresent) {
            val stockEntity = StockEntity(setStockVO.stock, optionalProduct.get())
            stockRepository.save(stockEntity)
            return StockVO(stockEntity.stockId, stockEntity.product.productId, stockEntity.stock)
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
    }
}