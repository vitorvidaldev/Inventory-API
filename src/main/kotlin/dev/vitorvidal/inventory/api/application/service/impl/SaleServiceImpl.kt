package dev.vitorvidal.inventory.api.application.service.impl

import dev.vitorvidal.inventory.api.application.service.SaleService
import dev.vitorvidal.inventory.api.domain.entity.Sale
import dev.vitorvidal.inventory.api.domain.repository.SaleRepository
import dev.vitorvidal.inventory.api.domain.vo.sale.SaleVO
import dev.vitorvidal.inventory.api.domain.vo.sale.SellVO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class SaleServiceImpl(val saleRepository: SaleRepository) : SaleService {
    override fun getSaleData(saleId: UUID): SaleVO {
        val optionalSale = saleRepository.findById(saleId)
        if (optionalSale.isPresent) {
            val sale = optionalSale.get()
            return SaleVO(
                sale.saleId,
                sale.productId!!,
                sale.buyerId!!
            )
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Error getting sale data")
    }

    override fun getUserPurchaseHistory(userId: UUID): Page<SaleVO> {
        val buyerPurchaseData = saleRepository.findByBuyerId(userId, Pageable.ofSize(10))
        return buyerPurchaseData.map { data ->
            SaleVO(data.saleId, data.productId!!, data.buyerId!!)
        }
    }

    override fun sell(sellVO: SellVO): SaleVO {
        var sale = Sale()
        sale.buyerId = sellVO.userId
        sale.productId = sellVO.productId
        sale = saleRepository.save(sale)
        // TODO use entity manager
        return SaleVO(sale.saleId, sale.productId!!, sale.buyerId!!)
    }
}