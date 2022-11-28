package com.mykhailo.vasylenko.core.db.data_store

import com.mykhailo.vasylenko.core.db.db.ExchangeHistoryDao
import com.mykhailo.vasylenko.core.db.entity.ExchangeHistoryEntity
import com.mykhailo.vasylenko.core.db.models.ExchangeHistory
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

internal class ExchangeHistoryDataStoreImpl @Inject constructor(
    private val dao: ExchangeHistoryDao
) : ExchangeHistoryDataStore {

    override suspend fun setHistory(item: ExchangeHistory) {
        dao.setHistory(item.toEntity())
    }

    override suspend fun getHistory(limit: Int): List<ExchangeHistory> =
        dao
            .getHistory(limit)
            .map {
                it.toModel()
            }


    private fun ExchangeHistory.toEntity() =
        ExchangeHistoryEntity(
            targetCurrencyCode = targetCurrencyCode,
            originCurrencyCode = originCurrencyCode,
            valueOrigin = valueOrigin,
            valueTarget = valueTarget,
            timestamp = timestamp.toEpochSecond(ZoneOffset.UTC)
        )

    private fun ExchangeHistoryEntity.toModel() =
        ExchangeHistory(
            timestamp = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC),
            targetCurrencyCode = targetCurrencyCode,
            originCurrencyCode = originCurrencyCode,
            valueOrigin = valueOrigin,
            valueTarget = valueTarget
        )


}