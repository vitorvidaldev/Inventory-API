package dev.vitorvidal.inventory.api.domain.vo.stock

import java.util.*

data class StockVO(val stockId: UUID, val productId: UUID, val value: Int)