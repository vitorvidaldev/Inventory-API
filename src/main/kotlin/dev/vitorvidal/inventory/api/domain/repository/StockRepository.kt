package dev.vitorvidal.inventory.api.domain.repository

import dev.vitorvidal.inventory.api.domain.entity.ProductEntity
import dev.vitorvidal.inventory.api.domain.entity.StockEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StockRepository : CrudRepository<StockEntity, UUID> {
    fun findByProduct(product: ProductEntity): Optional<StockEntity>
}