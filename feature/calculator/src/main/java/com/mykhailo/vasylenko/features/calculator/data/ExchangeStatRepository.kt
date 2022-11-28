package com.mykhailo.vasylenko.features.calculator.data

import com.mykhailo.vasylenko.features.calculator.domain.model.ExchangeStat
import java.time.LocalDateTime

interface ExchangeStatRepository {

    suspend fun getExchangeStats(
        currencyCode: String?,
        date: LocalDateTime
    ): List<ExchangeStat>
}