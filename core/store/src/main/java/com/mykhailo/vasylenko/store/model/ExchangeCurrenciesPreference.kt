package com.mykhailo.vasylenko.store.model

@kotlinx.serialization.Serializable
data class ExchangeCurrenciesPreference(
    val list: List<ExchangeCurrencyPreference>
)
