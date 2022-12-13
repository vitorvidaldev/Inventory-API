package dev.vitorvidal.inventory.api.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "stock")
class Stock() {
    constructor(stock: Int, product: Product) : this() {
        this.value = stock
        this.product = product
        this.creationDate = LocalDateTime.now()
        this.lastUpdateDate = LocalDateTime.now()
    }

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

    @OneToOne(optional = false)
    @JoinColumn(name = "product_product_id", unique = true, nullable = false)
    lateinit var product: Product
}