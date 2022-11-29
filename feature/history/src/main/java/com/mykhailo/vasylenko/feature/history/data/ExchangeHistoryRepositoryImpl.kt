package com.mykhailo.vasylenko.feature.history.data

import com.mykhailo.vasylenko.core.db.data_store.history.ExchangeHistoryDataStore
import com.mykhailo.vasylenko.feature.history.domain.ExchangeTransaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import javax.inject.Inject

internal class ExchangeHistoryRepositoryImpl @Inject constructor(
    private val dataSource: ExchangeHistoryDataStore
) : ExchangeHistoryRepository {

    override suspend fun loadHistory(): List<ExchangeTransaction> =
        dataSource
            .getHistory(limit = 10)
            .map {
                ExchangeTransaction(
                    timestamp = it.timestamp.toDisplayFormat(),
                    transaction = createTransactionText(
                        targetCurrencyCode = it.targetCurrencyCode,
                        originCurrencyCode = it.originCurrencyCode,
                        valueOrigin = it.valueOrigin,
                        valueTarget = it.valueTarget
                    )
                )
            }

    private fun LocalDateTime.toDisplayFormat(): String =
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).run {
            format(this)
        }

    private fun createTransactionText(
        targetCurrencyCode: String,
        originCurrencyCode: String,
        valueOrigin: String,
        valueTarget: String
    ): String = "$originCurrencyCode $valueOrigin -> $targetCurrencyCode $valueTarget "

}