package com.mykhailo.vasylenko.core.db.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mykhailo.vasylenko.core.db.entity.ExchangeHistoryEntity
import com.mykhailo.vasylenko.core.db.entity.ExchangeRateEntity

@Database(
    entities = [
        ExchangeHistoryEntity::class,
        ExchangeRateEntity::class
    ],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): ExchangeHistoryDao
    abstract fun ratesDao(): ExchangeRateDao
}