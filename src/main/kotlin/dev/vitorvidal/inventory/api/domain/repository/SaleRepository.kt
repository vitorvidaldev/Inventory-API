package dev.vitorvidal.inventory.api.domain.repository

import dev.vitorvidal.inventory.api.domain.entity.Sale
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SaleRepository : CrudRepository<Sale, UUID> {
    fun findByBuyerId(buyerId: UUID, pageable: Pageable): Page<Sale>
}