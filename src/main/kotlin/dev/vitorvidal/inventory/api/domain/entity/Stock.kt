package dev.vitorvidal.inventory.api.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity(name = "Stock")
@Table(name = "stock")
class Stock {
    @Id
    @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val stockId: UUID? = null

    @Column(name = "stock")
    var value: Int? = null

    @Column(name = "creation_date")
    var creationDate: LocalDateTime? = null

    @Column(name = "last_update_date")
    var lastUpdateDate: LocalDateTime? = null

    @OneToOne(optional = false)
    @JoinColumn(name = "product_product_id", unique = true, nullable = false)
    var product: Product? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Stock) return false

        if (stockId != other.stockId) return false

        return true
    }

    override fun hashCode(): Int = stockId?.hashCode() ?: 0

    override fun toString(): String {
        return "Stock(stockId=$stockId, value=$value, creationDate=$creationDate, lastUpdateDate=$lastUpdateDate)"
    }
}