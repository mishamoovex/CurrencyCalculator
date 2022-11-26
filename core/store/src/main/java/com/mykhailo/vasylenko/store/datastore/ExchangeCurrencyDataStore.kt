package com.mykhailo.vasylenko.store.datastore

import com.mykhailo.vasylenko.store.model.ExchangeCurrencyPreference

interface ExchangeCurrencyDataStore {

    suspend fun setCurrencies(data: List<ExchangeCurrencyPreference>)

    suspend fun getCurrencies(): List<ExchangeCurrencyPreference>
}