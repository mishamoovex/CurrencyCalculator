package com.mykhailo.vasylenko.app.data

import com.mykhailo.vasylenko.network.api.ApiNbu
import com.mykhailo.vasylenko.network.model.NetworkStatDataItem
import com.mykhailo.vasylenko.store.datastore.ExchangeCurrencyDataStore
import com.mykhailo.vasylenko.store.model.ExchangeCurrencyPreference
import javax.inject.Inject

internal class ExchangeCurrenciesRepositoryImpl @Inject constructor(
    private val api: ApiNbu,
    private val dataStore: ExchangeCurrencyDataStore
) : ExchangeCurrenciesRepository {

    override suspend fun loadExchangeCurrencies() {
        api.getState(
            date = null,
            currencyCode = null
        )
            .map { it.toPreferenceItem() }
            .also { dataStore.setCurrencies(it) }
    }

    private fun NetworkStatDataItem.toPreferenceItem() =
        ExchangeCurrencyPreference(
            name = displayName,
            code = currencyCode
        )
}