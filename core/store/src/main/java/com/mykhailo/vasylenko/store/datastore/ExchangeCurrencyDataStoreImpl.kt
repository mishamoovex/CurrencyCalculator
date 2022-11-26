package com.mykhailo.vasylenko.store.datastore

import androidx.datastore.core.DataStore
import com.mykhailo.vasylenko.store.model.ExchangeCurrenciesPreference
import com.mykhailo.vasylenko.store.model.ExchangeCurrencyPreference
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class ExchangeCurrencyDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<ExchangeCurrenciesPreference>
) : ExchangeCurrencyDataStore {

    override suspend fun setCurrencies(data: List<ExchangeCurrencyPreference>) {
        dataStore.updateData {
            it.copy(list = data)
        }
    }

    override suspend fun getCurrencies(): List<ExchangeCurrencyPreference> =
        dataStore.data.first().list


}