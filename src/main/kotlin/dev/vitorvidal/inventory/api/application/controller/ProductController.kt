package dev.vitorvidal.inventory.api.application.controller

import dev.vitorvidal.inventory.api.application.service.ProductService
import dev.vitorvidal.inventory.api.domain.vo.ProductVO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("rest/v1/product")
class ProductController(val productService: ProductService) {

    @GetMapping("/{productId}")
    fun getProductById(@PathVariable(value = "productId") productId: UUID): ResponseEntity<List<ProductVO>> {

        return ResponseEntity.status(HttpStatus.CREATED).body(null)
    }
}