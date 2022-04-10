package dev.vitorvidal.inventory.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InventoryApiApplication

fun main(args: Array<String>) {
	runApplication<InventoryApiApplication>(*args)
}
