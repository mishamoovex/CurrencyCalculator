package com.mykhailo.vasylenko.app.data.currency

import com.mykhailo.vasylenko.app.domain.model.ExchangeCurrency

interface ExchangeCurrenciesRepository {

    suspend fun loadExchangeCurrencies()

    suspend fun getExchangeCurrencies(): List<ExchangeCurrency>
}