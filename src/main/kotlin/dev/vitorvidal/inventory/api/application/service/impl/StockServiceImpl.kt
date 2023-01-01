package dev.vitorvidal.inventory.api.application.service.impl

import dev.vitorvidal.inventory.api.application.service.StockService
import dev.vitorvidal.inventory.api.domain.entity.Stock
import dev.vitorvidal.inventory.api.domain.repository.ProductRepository
import dev.vitorvidal.inventory.api.domain.repository.StockRepository
import dev.vitorvidal.inventory.api.domain.vo.stock.SetStockVO
import dev.vitorvidal.inventory.api.domain.vo.stock.StockVO
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class StockServiceImpl(val stockRepository: StockRepository, val productRepository: ProductRepository) : StockService {
    override fun getProductStock(productId: UUID): StockVO {
        val product = productRepository.findById(productId).get()
        val optionalStock: Optional<Stock> = stockRepository.findByProduct(product)

        if (optionalStock.isPresent) {
            val stockEntity = optionalStock.get()

            return StockVO(
                stockEntity.stockId,
                stockEntity.product.productId,
                stockEntity.value
            )
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found for product $productId")
    }

    override fun setProductStock(setStockVO: SetStockVO): StockVO {
        if (setStockVO.stockId != null) {
            val optionalStock = stockRepository.findById(setStockVO.stockId)
            if (optionalStock.isPresent) {
                val stockEntity = optionalStock.get()
                stockEntity.value = setStockVO.value
                stockRepository.save(stockEntity)
                return StockVO(stockEntity.stockId, stockEntity.product.productId, stockEntity.value)
            }
        }

        val product = productRepository.findById(setStockVO.productId).get()
        val stock = Stock()
        stock.value = setStockVO.value
        stock.product = product
        // TODO use entity manager

        stockRepository.save(stock)
        return StockVO(stock.stockId, stock.product.productId, stock.value)
    }
}