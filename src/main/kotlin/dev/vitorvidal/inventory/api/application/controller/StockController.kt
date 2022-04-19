package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.StockService
import dev.vitorvidal.inventory.api.domain.vo.stock.AddStockVO
import dev.vitorvidal.inventory.api.domain.vo.stock.StockVO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/rest/v1/stocks")
class StockController(val stockService: StockService) {

    @GetMapping("/{productId}")
    fun getCurrentProductStock(@PathVariable(name = "productId") productId: UUID): ResponseEntity<StockVO> {
        val stockVO: StockVO = stockService.getCurrentProductStock(productId)
        return ResponseEntity.ok().body(stockVO)
    }

    // TODO post vs put vs patch
    @PostMapping
    fun addProductStock(@RequestBody @Valid addStockVO: AddStockVO): ResponseEntity<StockVO> {
        val stockVO: StockVO = stockService.addProductStock(addStockVO)
        return ResponseEntity.status(HttpStatus.CREATED).body(stockVO)
    }
}