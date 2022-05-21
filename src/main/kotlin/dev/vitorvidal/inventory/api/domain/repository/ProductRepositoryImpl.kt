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
    override fun getFilter(productName: String?, productBrand: String?, page: Pageable): Page<ProductEntity> {
        val builder: CriteriaBuilder = em.criteriaBuilder
        val query: CriteriaQuery<ProductEntity> = builder.createQuery(ProductEntity::class.java)

        val product: Root<ProductEntity> = query.from(ProductEntity::class.java)
        val predicates: MutableList<Predicate> = ArrayList()

        if (productBrand != null) {
            predicates.add(builder.equal(product.get<String>("productBrand"), productBrand))
        }
        if (productName != null) {
            predicates.add(builder.like(product.get("productName"), "%$productName%"))
        }


        val countQuery: CriteriaQuery<Long> = builder.createQuery(Long::class.java)
        val entity_: Root<ProductEntity> = countQuery.from(query.resultType)
        entity_.alias("ProductEntity")

        countQuery.select(builder.count(entity_))
        val restriction: Predicate? = query.restriction
        if (restriction != null) {
            countQuery.where(restriction)
        }
        val totalCount: Long = em.createQuery(countQuery).singleResult


        val where = query.where(*predicates.toTypedArray())

        val resultList = em.createQuery(where)
            .setFirstResult(page.offset.toInt())
            .setMaxResults(page.pageSize)
            .resultList
        return PageableExecutionUtils.getPage(resultList, page) { totalCount }
    }
}