package com.mykhailo.vasylenko.core.db.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mykhailo.vasylenko.core.db.entity.ExchangeHistoryEntity

@Dao
internal interface ExchangeHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setHistory(item: ExchangeHistoryEntity)

    @Query("SELECT * FROM exchange_history ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getHistory(limit: Int): List<ExchangeHistoryEntity>

}