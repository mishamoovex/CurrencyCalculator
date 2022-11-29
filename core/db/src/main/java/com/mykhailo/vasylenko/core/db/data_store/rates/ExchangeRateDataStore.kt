package com.mykhailo.vasylenko.core.db.data_store.rates

import com.mykhailo.vasylenko.core.db.models.ExchangeRate

interface ExchangeRateDataStore {

    suspend fun updateRates(rates: List<ExchangeRate>)

    suspend fun getRate(code: String): Double
}