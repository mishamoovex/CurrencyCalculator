package com.mykhailo.vasylenko.features.calculator.domain

interface ConvertCurrencyUseCase {

    suspend operator fun invoke(
        isOrigin: Boolean,
        value: String,
        currencyCode: String
    ): String
}