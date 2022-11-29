package com.mykhailo.vasylenko.features.calculator.data.stat

import java.time.LocalDate

interface ExchangeStatRepository {

    suspend fun loadStat(date: LocalDate?)

    suspend fun getRate(currencyCode: String): Double
}