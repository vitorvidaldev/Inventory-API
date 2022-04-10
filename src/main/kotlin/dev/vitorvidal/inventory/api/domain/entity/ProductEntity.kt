package dev.vitorvidal.inventory.api.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "product")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var uuid: UUID = UUID.randomUUID()
) {

}