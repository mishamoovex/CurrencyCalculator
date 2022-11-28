package com.mykhailo.vasylenko.feature.currency.data

import com.mykhailo.vasylenko.feature.currency.domain.ExchangeCurrency

interface CurrencyRepository {

    suspend fun getCurrencies(): List<ExchangeCurrency>
}