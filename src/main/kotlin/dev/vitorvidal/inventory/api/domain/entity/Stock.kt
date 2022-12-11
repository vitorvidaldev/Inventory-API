package dev.vitorvidal.inventory.api.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "stock")
class Stock() {
    @Id
    @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var stockId: UUID = UUID.randomUUID()

    @Column(name = "stock")
    var value: Int = 0

    @Column(name = "creation_date")
    lateinit var creationDate: LocalDateTime

    @Column(name = "last_update_date")
    lateinit var lastUpdateDate: LocalDateTime

    @Column(name = "product_id")
    lateinit var productId: UUID

    constructor(stock: Int, productId: UUID) : this() {
        this.value = stock
        this.productId = productId
        this.creationDate = LocalDateTime.now()
        this.lastUpdateDate = LocalDateTime.now()
    }
}