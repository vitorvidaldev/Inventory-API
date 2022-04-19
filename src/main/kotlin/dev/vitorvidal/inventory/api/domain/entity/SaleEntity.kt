package dev.vitorvidal.inventory.api.domain.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "sale")
class SaleEntity {
    @Id
    @Column(name = "sale_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var saleId: UUID = UUID.randomUUID()

    @Column(name = "creation_date")
    val creationDate: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: ProductEntity? = null
}