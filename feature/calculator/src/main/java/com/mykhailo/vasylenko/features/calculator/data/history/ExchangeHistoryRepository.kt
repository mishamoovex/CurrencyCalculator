package com.mykhailo.vasylenko.features.calculator.data.history

internal interface ExchangeHistoryRepository {

    suspend fun saveTransaction(
        targetCurrencyCode: String,
        originCurrencyCode: String,
        valueOrigin: String,
        valueTarget: String
    )
}