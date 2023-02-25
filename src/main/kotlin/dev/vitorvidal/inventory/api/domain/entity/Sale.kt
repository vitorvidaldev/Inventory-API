package dev.vitorvidal.inventory.api.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity(name = "Sale")
@Table(name = "sale")
class Sale {
    @Id
    @Column(name = "sale_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var saleId: UUID = UUID.randomUUID()

    @Column(name = "creation_date")
    var creationDate: LocalDateTime = LocalDateTime.now()

    @Column(name = "product_id")
    var productId: UUID? = null

    @Column(name = "buyer_id")
    var buyerId: UUID? = null
}