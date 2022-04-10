package dev.vitorvidal.inventory.api.application.service

import org.springframework.stereotype.Service

@Service
class SaleService(val saleReportService: ReportService)