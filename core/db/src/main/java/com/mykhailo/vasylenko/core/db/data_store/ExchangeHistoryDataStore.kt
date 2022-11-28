package com.mykhailo.vasylenko.core.db.data_store

import com.mykhailo.vasylenko.core.db.models.ExchangeHistory

interface ExchangeHistoryDataStore {

    suspend fun getHistory(limit: Int = 10): List<ExchangeHistory>

    suspend fun setHistory(item: ExchangeHistory)
}