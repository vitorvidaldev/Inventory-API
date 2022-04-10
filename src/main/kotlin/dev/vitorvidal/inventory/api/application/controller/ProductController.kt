package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.ProductService
import dev.vitorvidal.inventory.api.domain.vo.product.ProductVO
import dev.vitorvidal.inventory.api.domain.vo.product.RegisterProductVO
import dev.vitorvidal.inventory.api.domain.vo.product.UpdateProductVO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("rest/v1/product")
class ProductController(val productService: ProductService) {

    @GetMapping
    fun getProductList(): ResponseEntity<List<ProductVO>> {
        val productVOList: List<ProductVO> = productService.getProductList()
        return ResponseEntity.ok().body(productVOList)
    }

    @GetMapping("/{productId}")
    fun getProductById(@PathVariable(value = "productId") productId: UUID): ResponseEntity<ProductVO> {
        val productVO: ProductVO = productService.getProductById(productId)
        return ResponseEntity.ok().body(productVO)
    }

    @PostMapping
    fun registerProduct(@RequestBody @Valid registerProductVO: RegisterProductVO): ResponseEntity<ProductVO> {
        val productVO: ProductVO = productService.registerProduct(registerProductVO)
        return ResponseEntity.status(HttpStatus.CREATED).body(productVO)
    }

    @PatchMapping("/{productId}")
    fun updateStock(
        @PathVariable(value = "productId") productId: UUID,
        @RequestBody @Valid updateProductVO: UpdateProductVO
    ): ResponseEntity<ProductVO> {
        val productVO: ProductVO = productService.updateStock(productId, updateProductVO)
        return ResponseEntity.ok().body(productVO)
    }
}