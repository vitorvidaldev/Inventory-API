package dev.vitorvidal.inventory.api.domain.repository

import dev.vitorvidal.inventory.api.domain.entity.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Repository
class ProductRepositoryImpl(private val em: EntityManager) : ProductRepositoryCustom {
    override fun findByFilter(productName: String?, productBrand: String?, page: Pageable): Page<ProductEntity> {
        val builder: CriteriaBuilder = em.criteriaBuilder
        val criteriaQuery: CriteriaQuery<ProductEntity> = builder.createQuery(ProductEntity::class.java)

        val product: Root<ProductEntity> = criteriaQuery.from(ProductEntity::class.java)
        val predicates: MutableList<Predicate> = ArrayList()

        if (productBrand != null) {
            predicates.add(builder.equal(product.get<String>("productBrand"), productBrand))
        }
        if (productName != null) {
            predicates.add(builder.like(product.get("productName"), "%$productName%"))
        }
        criteriaQuery.where(*predicates.toTypedArray())

        val countQuery = builder.createQuery(Long::class.java)
        val productsRootCount: Root<ProductEntity> = countQuery.from(ProductEntity::class.java)
        countQuery.select(builder.count(productsRootCount))
        val count = em.createQuery(countQuery).singleResult

        // TODO: fix pagination bug
        val products: List<ProductEntity> = em.createQuery(criteriaQuery).resultList
        return PageableExecutionUtils.getPage(products, page) { count }
    }
}