package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.vo.sale.SaleVO
import dev.vitorvidal.inventory.api.domain.vo.sale.SellVO
import org.springframework.data.domain.Page
import java.util.*

interface SaleService {
    fun getSaleData(saleId: UUID): SaleVO
    fun getUserPurchaseHistory(userId: UUID): Page<SaleVO>
    fun sell(sellVO: SellVO): SaleVO
}