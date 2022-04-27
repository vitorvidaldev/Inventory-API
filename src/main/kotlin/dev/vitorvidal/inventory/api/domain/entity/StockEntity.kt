package dev.vitorvidal.inventory.api.domain.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "stock")
class StockEntity() {
    @Id
    @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var stockId: UUID = UUID.randomUUID()

    @Column(name = "stock")
    var stock: Int = 0

    @Column(name = "creation_date")
    lateinit var creationDate: LocalDateTime

    @Column(name = "last_update_date")
    lateinit var lastUpdateDate: LocalDateTime

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    lateinit var product: ProductEntity

    constructor(stock: Int, productEntity: ProductEntity) : this() {
        this.stock = stock
        this.product = productEntity
        this.creationDate = LocalDateTime.now()
        this.lastUpdateDate = LocalDateTime.now()
    }
}