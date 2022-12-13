package dev.vitorvidal.inventory.api.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "product")
class Product() {
    constructor(productName: String, productBrand: String, productPrice: Double, user: User) : this() {
        this.productName = productName
        this.productBrand = productBrand
        this.productPrice = productPrice
        this.user = user
    }

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val productId: UUID = UUID.randomUUID()

    @Column(name = "product_name")
    var productName: String = ""

    @Column(name = "product_brand")
    var productBrand: String = ""

    @Column(name = "product_price", columnDefinition = "decimal NOT NULL")
    var productPrice: Double = 0.0

    @Column(name = "created_at")
    val creationDate: LocalDateTime = LocalDateTime.now()

    @Column(name = "is_active")
    val isActive: Boolean = true

    @Column(name = "updated_at")
    var lastUpdateDate: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    lateinit var user: User

    @OneToOne(optional = false, mappedBy = "product")
    lateinit var stock: Stock
}