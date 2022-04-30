package dev.vitorvidal.inventory.api.domain.repository

import dev.vitorvidal.inventory.api.domain.entity.ProductEntity
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class ProductSpecification(val criteria: ProductEntity) : Specification<ProductEntity> {
    override fun toPredicate(root: Root<ProductEntity>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
        TODO("Not yet implemented")
    }

}