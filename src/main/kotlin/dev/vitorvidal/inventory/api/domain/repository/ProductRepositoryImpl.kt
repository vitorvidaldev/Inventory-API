package dev.vitorvidal.inventory.api.domain.repository

import dev.vitorvidal.inventory.api.domain.entity.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


@Repository
class ProductRepositoryImpl(val em: EntityManager) : ProductRepositoryCustom {
    override fun findByFilter(productName: String?, productBrand: String?, page: Pageable): Page<ProductEntity> {
        val cb: CriteriaBuilder = em.criteriaBuilder
        val cq: CriteriaQuery<ProductEntity> = cb.createQuery(ProductEntity::class.java)

        val product: Root<ProductEntity> = cq.from(ProductEntity::class.java)
        val predicates: MutableList<Predicate> = ArrayList<Predicate>()

        if (productBrand != null) {
            predicates.add(cb.equal(product.get<String>("productBrand"), productBrand))
        }
        if (productName != null) {
            predicates.add(cb.like(product.get<String>("productName"), "%$productName%"))
        }
        cq.where(*predicates.toTypedArray())

        // TODO return pageable object
//        return em.createQuery(cq).resultList
        return Page.empty()
    }
}