package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.impl.SaleServiceImpl
import dev.vitorvidal.inventory.api.domain.vo.sale.SaleVO
import dev.vitorvidal.inventory.api.domain.vo.sale.SellVO
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/rest/v1/sales")
class SaleController(val saleServiceImpl: SaleServiceImpl) {

    @GetMapping("/{saleId}")
    fun getSaleData(@PathVariable(value = "saleId") saleId: UUID): ResponseEntity<SaleVO> {
        val saleVO: SaleVO = saleServiceImpl.getSaleData(saleId)
        return ResponseEntity.ok().body(saleVO)
    }

    @GetMapping("/user/{userId}")
    fun getUserPurchaseHistory(@PathVariable(value = "userId") userId: UUID): ResponseEntity<Page<SaleVO>> {
        val saleVO: Page<SaleVO> = saleServiceImpl.getUserPurchaseHistory(userId)
        return ResponseEntity.ok().body(saleVO)
    }

    @PostMapping
    fun sell(@RequestBody sellVO: SellVO): ResponseEntity<SaleVO> {
        val saleVO: SaleVO = saleServiceImpl.sell(sellVO)
        return ResponseEntity.status(HttpStatus.CREATED).body(saleVO)
    }
}