package dev.vitorvidal.inventory.api.domain.repository

import dev.vitorvidal.inventory.api.domain.entity.ProductEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : CrudRepository<ProductEntity, UUID>, ProductRepositoryCustom