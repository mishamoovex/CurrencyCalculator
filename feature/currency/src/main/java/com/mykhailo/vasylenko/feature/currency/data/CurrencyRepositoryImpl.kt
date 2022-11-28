package com.mykhailo.vasylenko.feature.currency.data

import com.mykhailo.vasylenko.feature.currency.domain.ExchangeCurrency
import com.mykhailo.vasylenko.store.datastore.ExchangeCurrencyDataStore
import javax.inject.Inject

internal class CurrencyRepositoryImpl @Inject constructor(
    private val dataStore: ExchangeCurrencyDataStore
) : CurrencyRepository {

    override suspend fun getCurrencies(): List<ExchangeCurrency> =
        dataStore
            .getCurrencies()
            .map {
                ExchangeCurrency(
                    code = it.code,
                    name = it.name
                )
            }

}