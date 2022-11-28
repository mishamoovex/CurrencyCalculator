package com.mykhailo.vasylenko.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_history")
internal data class ExchangeHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val timestamp: Long,
    val targetCurrencyCode: String,
    val originCurrencyCode: String,
    val valueOrigin: String,
    val valueTarget: String
)
