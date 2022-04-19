package dev.vitorvidal.inventory.api.domain.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "stock")
class StockEntity {
    @Id
    @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val stockId: UUID = UUID.randomUUID()

    @Column(name = "creation_date")
    val creationDate: LocalDateTime = LocalDateTime.now()

    @Column(name = "last_update_date")
    var lastUpdateDate: LocalDateTime = LocalDateTime.now()

    @OneToOne
    val product: ProductEntity? = null
}