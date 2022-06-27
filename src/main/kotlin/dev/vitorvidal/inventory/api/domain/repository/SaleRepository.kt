package dev.vitorvidal.inventory.api.domain.repository

import dev.vitorvidal.inventory.api.domain.entity.SaleEntity
import org.springframework.data.domain.Page
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SaleRepository : PagingAndSortingRepository<SaleEntity, UUID> {
    fun findByBuyerId(buyerId: UUID): Page<SaleEntity>
}