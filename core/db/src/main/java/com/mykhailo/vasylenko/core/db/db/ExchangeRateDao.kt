package com.mykhailo.vasylenko.core.db.db

import androidx.room.*
import com.mykhailo.vasylenko.core.db.entity.ExchangeRateEntity

@Dao
internal interface ExchangeRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<ExchangeRateEntity>)

    @Query("DELETE FROM exchange_rate")
    suspend fun deleteAll()

    @Transaction
    suspend fun resetRates(list: List<ExchangeRateEntity>) {
        deleteAll()
        insert(list)
    }

    @Query("SELECT rate FROM exchange_rate WHERE code =:code")
    suspend fun getRate(code: String): Double?

}