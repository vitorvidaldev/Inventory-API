package dev.vitorvidal.inventory.api.domain.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "product")
class ProductEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val productId: UUID = UUID.randomUUID()

    @Column(name = "productName")
    var productName: String = ""

    @Column(name = "productBrand")
    var productBrand: String = ""

    @Column(name = "creationDate")
    val creationDate: LocalDateTime = LocalDateTime.now()

    @Column(name = "lastUpdateDate")
    var lastUpdateDate: LocalDateTime = LocalDateTime.now()

    @Column
    @OneToMany
    val sales: List<SaleEntity>? = null

    @Column
    @OneToMany
    val stocks: List<StockEntity>? = null

    constructor(productName: String, productBrand: String) : this() {
        this.productName = productName
        this.productBrand = productBrand
        this.lastUpdateDate = LocalDateTime.now()
    }
}