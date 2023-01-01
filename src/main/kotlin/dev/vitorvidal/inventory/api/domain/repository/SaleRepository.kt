package dev.vitorvidal.inventory.api.domain.repository

import dev.vitorvidal.inventory.api.domain.entity.Sale
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SaleRepository : JpaRepository<Sale, UUID> {
    fun findByBuyerId(buyerId: UUID, pageable: Pageable): Page<Sale>
}