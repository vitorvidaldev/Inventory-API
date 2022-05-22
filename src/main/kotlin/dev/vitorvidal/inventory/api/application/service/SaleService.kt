package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.repository.SaleRepository
import dev.vitorvidal.inventory.api.domain.vo.sale.SaleVO
import dev.vitorvidal.inventory.api.domain.vo.sale.SellVO
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class SaleService(val saleRepository: SaleRepository) {
    fun getSaleData(saleId: UUID): SaleVO {
        TODO("Not yet implemented")
    }

    fun getUserPurchaseHistory(userId: UUID): Page<SaleVO> {
        TODO("Not yet implemented")
    }

    fun sell(sellVO: SellVO): SaleVO {
        TODO("Not yet implemented")
    }
}