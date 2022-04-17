package dev.vitorvidal.inventory.api.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "stock")
class StockEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val stockId: UUID = UUID.randomUUID()
}