package com.mykhailo.vasylenko.core.db.data_store.rates

import com.mykhailo.vasylenko.core.db.db.ExchangeRateDao
import com.mykhailo.vasylenko.core.db.entity.ExchangeRateEntity
import com.mykhailo.vasylenko.core.db.models.ExchangeRate
import javax.inject.Inject

internal class ExchangeRateDataStoreImpl @Inject constructor(
    private val dao: ExchangeRateDao
) : ExchangeRateDataStore {

    override suspend fun updateRates(rates: List<ExchangeRate>) {
        rates.map {
            ExchangeRateEntity(
                code = it.code,
                rate = it.rate
            )
        }.also {
            dao.resetRates(it)
        }
    }

    override suspend fun getRate(code: String): Double =
        dao.getRate(code) ?: throw IllegalStateException("Rate for currency: $code not found")
}