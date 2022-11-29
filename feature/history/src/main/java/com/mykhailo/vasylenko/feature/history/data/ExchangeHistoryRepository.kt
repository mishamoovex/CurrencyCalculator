package com.mykhailo.vasylenko.feature.history.data

import com.mykhailo.vasylenko.feature.history.domain.ExchangeTransaction

interface ExchangeHistoryRepository {

    suspend fun loadHistory(): List<ExchangeTransaction>
}