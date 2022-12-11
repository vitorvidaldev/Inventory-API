package dev.vitorvidal.inventory.api.domain.repository

import dev.vitorvidal.inventory.api.domain.entity.Stock
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StockRepository : CrudRepository<Stock, UUID> {
    fun findByProductId(productId: UUID): Optional<Stock>
}