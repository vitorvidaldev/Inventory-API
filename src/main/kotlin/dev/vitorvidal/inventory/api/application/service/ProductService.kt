package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository)