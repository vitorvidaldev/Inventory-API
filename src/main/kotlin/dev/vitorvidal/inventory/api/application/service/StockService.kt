package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.vo.stock.SetStockVO
import dev.vitorvidal.inventory.api.domain.vo.stock.StockVO
import java.util.*

interface StockService {
    fun getProductStock(productId: UUID): StockVO
    fun setProductStock(setStockVO: SetStockVO): StockVO
}