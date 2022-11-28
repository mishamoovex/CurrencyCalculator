package com.mykhailo.vasylenko.core.db.models

import java.time.LocalDateTime

data class ExchangeHistory(
    val timestamp: LocalDateTime,
    val targetCurrencyCode: String,
    val originCurrencyCode: String,
    val valueOrigin: String,
    val valueTarget: String
)
