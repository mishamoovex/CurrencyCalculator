package com.mykhailo.vasylenko.features.calculator.domain

import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class ConvertCurrencyUseCaseImpl @Inject constructor() : ConvertCurrencyUseCase {

    private val targetRate = 1.5

    override suspend fun invoke(
        isOrigin: Boolean,
        value: String,
        currencyCode: String
    ): String {
        val currency = value.toDouble().round()
        val rate = getRate(currencyCode)
        return if (isOrigin) {
            currency * rate
        } else {
            currency / rate
        }
            .round()
            .toString()
    }

    private fun Double.round(): Double{
        val df = DecimalFormat("#.##").apply {
            roundingMode = RoundingMode.CEILING
        }
        return df.format(this).toDouble()
    }

    private fun getRate(currencyCode: String) = targetRate
}