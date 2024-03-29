package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.impl.StockServiceImpl
import dev.vitorvidal.inventory.api.domain.vo.stock.SetStockVO
import dev.vitorvidal.inventory.api.domain.vo.stock.StockVO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/rest/v1/stocks")
class StockController(val stockServiceImpl: StockServiceImpl) {

    @Operation(summary = "Get product stock by id")
    @ApiResponse(
        responseCode = "200",
        description = "Retrieve product stock"
    )
    @GetMapping("/{productId}")
    fun getCurrentProductStock(@PathVariable(name = "productId") productId: UUID): ResponseEntity<StockVO> {
        val stockVO: StockVO = stockServiceImpl.getProductStock(productId)
        return ResponseEntity.ok().body(stockVO)
    }

    @Operation(summary = "Update product stock")
    @ApiResponse(
        responseCode = "201",
        description = "Update product stock"
    )
    @PostMapping
    fun updateProductStock(@RequestBody @Valid setStockVO: SetStockVO): ResponseEntity<StockVO> {
        val stockVO: StockVO = stockServiceImpl.setProductStock(setStockVO)
        return ResponseEntity.status(HttpStatus.CREATED).body(stockVO)
    }
}