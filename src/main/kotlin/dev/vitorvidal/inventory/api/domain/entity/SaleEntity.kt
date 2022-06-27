package dev.vitorvidal.inventory.api.domain.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "sale")
class SaleEntity() {
    @Id
    @Column(name = "sale_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var saleId: UUID = UUID.randomUUID()

    @Column(name = "creation_date")
    val creationDate: LocalDateTime = LocalDateTime.now()

    @Column(name = "product_id")
    lateinit var productId: UUID

    @Column(name = "buyer_id")
    lateinit var buyerId: UUID

    constructor(productId: UUID, buyerId: UUID) : this() {
        this.productId = productId
        this.buyerId = buyerId
    }
}