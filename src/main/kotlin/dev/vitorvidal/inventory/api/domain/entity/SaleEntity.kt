package dev.vitorvidal.inventory.api.domain.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "sale")
class SaleEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var saleId: UUID = UUID.randomUUID()

    @Column(name = "creationDate")
    val creationDate: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    val product: ProductEntity? = null
}