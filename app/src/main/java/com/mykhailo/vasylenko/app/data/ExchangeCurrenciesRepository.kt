package com.mykhailo.vasylenko.app.data


internal interface ExchangeCurrenciesRepository {

    suspend fun loadExchangeCurrencies()

}