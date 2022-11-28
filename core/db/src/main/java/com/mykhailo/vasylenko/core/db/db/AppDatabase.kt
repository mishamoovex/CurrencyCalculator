package com.mykhailo.vasylenko.core.db.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mykhailo.vasylenko.core.db.entity.ExchangeHistoryEntity

@Database(entities = [ExchangeHistoryEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): ExchangeHistoryDao
}