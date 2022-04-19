package dev.vitorvidal.inventory.api.domain.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "stock")
class StockEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val stockId: UUID = UUID.randomUUID()

    @Column(name = "creationDate")
    val creationDate: LocalDateTime = LocalDateTime.now()

    @Column(name = "lastUpdateDate")
    var lastUpdateDate: LocalDateTime = LocalDateTime.now()

    @Column
    @ManyToOne
    val product: ProductEntity? = null

}