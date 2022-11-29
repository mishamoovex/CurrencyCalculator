package com.mykhailo.vasylenko.features.calculator.data.stat

import com.mykhailo.vasylenko.core.db.data_store.rates.ExchangeRateDataStore
import com.mykhailo.vasylenko.core.db.models.ExchangeRate
import com.mykhailo.vasylenko.network.api.ApiNbu
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal class ExchangeStatRepositoryImpl @Inject constructor(
    private val api: ApiNbu,
    private val ratesDataStore: ExchangeRateDataStore
) : ExchangeStatRepository {

    override suspend fun loadStat(
        date: LocalDate?
    ) {
        api.getState(date = date?.toRequestFormat())
            .map {
                ExchangeRate(
                    code = it.currencyCode,
                    rate = it.rate
                )
            }
            .also {
                ratesDataStore.updateRates(it)
            }
    }

    override suspend fun getRate(currencyCode: String): Double =
        ratesDataStore.getRate(currencyCode)

    private fun LocalDate.toRequestFormat(): String =
        this.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
}