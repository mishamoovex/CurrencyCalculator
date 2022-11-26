package com.mykhailo.vasylenko.store.model

@kotlinx.serialization.Serializable
data class ExchangeCurrencyPreference(
    val name: String,
    val code: String
)
