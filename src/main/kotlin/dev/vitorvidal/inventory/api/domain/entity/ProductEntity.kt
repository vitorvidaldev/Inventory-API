package dev.vitorvidal.inventory.api.domain.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "product")
class ProductEntity() {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val productId: UUID = UUID.randomUUID()

    @Column(name = "product_name")
    var productName: String = ""

    @Column(name = "product_brand")
    var productBrand: String = ""

    @Column(name = "creation_date")
    val creationDate: LocalDateTime = LocalDateTime.now()

    @Column(name = "last_update_date")
    var lastUpdateDate: LocalDateTime = LocalDateTime.now()

    @Column
    @OneToMany
    val sales: List<SaleEntity>? = null

    @OneToOne
    @JoinColumn(name = "stock_id")
    val stock: StockEntity? = null

    constructor(productName: String, productBrand: String) : this() {
        this.productName = productName
        this.productBrand = productBrand
        this.lastUpdateDate = LocalDateTime.now()
    }
}