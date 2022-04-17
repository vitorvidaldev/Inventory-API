package dev.vitorvidal.inventory.api.application.service

import dev.vitorvidal.inventory.api.domain.repository.StockRepository
import org.springframework.stereotype.Service

@Service
class StockService(stockRepository: StockRepository)