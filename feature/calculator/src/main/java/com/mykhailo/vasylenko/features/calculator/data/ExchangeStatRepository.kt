package com.mykhailo.vasylenko.features.calculator.data

import java.time.LocalDate

interface ExchangeStatRepository {

    suspend fun loadStat(date: LocalDate?)
}