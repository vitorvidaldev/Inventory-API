package dev.vitorvidal.inventory.api.domain.repository

import dev.vitorvidal.inventory.api.domain.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductRepositoryCustom {

    fun findByFilter(productName: String?, productBrand: String?, page: Pageable): Page<Product>
}