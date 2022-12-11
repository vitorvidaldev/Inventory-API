package dev.vitorvidal.inventory.api.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "sale")
class Sale() {
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