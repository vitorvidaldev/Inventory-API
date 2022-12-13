package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.impl.ProductServiceImpl
import dev.vitorvidal.inventory.api.domain.vo.product.ProductVO
import dev.vitorvidal.inventory.api.domain.vo.product.RegisterProductVO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("rest/v1/products")
class ProductController(val productService: ProductServiceImpl) {

    @Operation(summary = "Returns product list")
    @ApiResponse(responseCode = "200", description = "Retrieve product list")
    @GetMapping
    fun getProducts(
        @RequestParam(value = "name", required = false) productName: String?,
        @RequestParam(value = "brand", required = false) productBrand: String?,
        @RequestParam(value = "page", required = false, defaultValue = "0") pageNumber: Int
    ): ResponseEntity<Page<ProductVO>> {
        val productVOList: Page<ProductVO> = productService.getProducts(productName, productBrand, pageNumber)
        return ResponseEntity.ok().body(productVOList)
    }

    @Operation(summary = "Get product data by id")
    @ApiResponse(responseCode = "200", description = "Retrieve product data")
    @GetMapping("/{productId}")
    fun getProductById(@PathVariable(value = "productId") productId: UUID): ResponseEntity<ProductVO> {
        val productVO: ProductVO = productService.getProductById(productId)
        return ResponseEntity.ok().body(productVO)
    }

    @Operation(summary = "Register product data")
    @ApiResponse(responseCode = "201", description = "Register new product")
    @PostMapping
    fun registerProduct(@RequestBody @Valid registerProductVO: RegisterProductVO): ResponseEntity<ProductVO> {
        val productVO: ProductVO = productService.registerProduct(registerProductVO)
        return ResponseEntity.status(HttpStatus.CREATED).body(productVO)
    }

    @Operation(summary = "Delete product by id")
    @ApiResponse(responseCode = "204, 404", description = "Deletes product data")
    @DeleteMapping("/{productId}")
    fun removeProduct(@PathVariable(value = "productId") productId: UUID): ResponseEntity<Void> {
        return productService.removeProduct(productId)
    }
}