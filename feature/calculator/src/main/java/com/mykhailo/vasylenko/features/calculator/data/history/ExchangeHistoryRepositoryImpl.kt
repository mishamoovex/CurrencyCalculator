package com.mykhailo.vasylenko.features.calculator.data.history

import com.mykhailo.vasylenko.core.db.data_store.ExchangeHistoryDataStore
import com.mykhailo.vasylenko.core.db.models.ExchangeHistory
import java.time.LocalDateTime
import javax.inject.Inject

internal class ExchangeHistoryRepositoryImpl @Inject constructor(
    private val dataStore: ExchangeHistoryDataStore
) : ExchangeHistoryRepository {

    override suspend fun saveTransaction(
        targetCurrencyCode: String,
        originCurrencyCode: String,
        valueOrigin: String,
        valueTarget: String
    ) {
        dataStore.setHistory(
            ExchangeHistory(
                timestamp = LocalDateTime.now(),
                targetCurrencyCode = targetCurrencyCode,
                originCurrencyCode = originCurrencyCode,
                valueOrigin = valueOrigin,
                valueTarget = valueTarget
            )
        )
    }
}