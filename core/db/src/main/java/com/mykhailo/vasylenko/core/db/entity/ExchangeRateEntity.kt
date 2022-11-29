package com.mykhailo.vasylenko.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rate")
internal data class ExchangeRateEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val code: String,
    val rate: Double
)
