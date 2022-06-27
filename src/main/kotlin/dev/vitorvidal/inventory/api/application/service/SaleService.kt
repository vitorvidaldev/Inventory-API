package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.entity.SaleEntity
import dev.vitorvidal.inventory.api.domain.repository.SaleRepository
import dev.vitorvidal.inventory.api.domain.vo.sale.SaleVO
import dev.vitorvidal.inventory.api.domain.vo.sale.SellVO
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class SaleService(val saleRepository: SaleRepository) {
    fun getSaleData(saleId: UUID): SaleVO {
        val optionalSale = saleRepository.findById(saleId)
        if (optionalSale.isPresent) {
            val sale = optionalSale.get()
            return SaleVO(
                sale.saleId,
                sale.productId,
                sale.buyerId
            )
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Error getting sale data")
    }

    fun getUserPurchaseHistory(userId: UUID): Page<SaleVO> {
        val buyerPurchaseData = saleRepository.findByBuyerId(userId)
        return buyerPurchaseData.map { data ->
            SaleVO(data.saleId, data.productId, data.buyerId)
        }
    }

    fun sell(sellVO: SellVO): SaleVO {
        val saleEntity = saleRepository.save(SaleEntity(sellVO.userId, sellVO.productId))
        return SaleVO(saleEntity.saleId, saleEntity.productId, saleEntity.buyerId)
    }
}