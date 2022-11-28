package com.mykhailo.vasylenko.features.calculator.domain

import com.mykhailo.vasylenko.features.calculator.data.history.ExchangeHistoryRepository
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

internal class ConvertCurrencyUseCaseImpl @Inject constructor(
    private val repository: ExchangeHistoryRepository
) : ConvertCurrencyUseCase {

    private val formatter = DecimalFormat("#.##").apply {
        roundingMode = RoundingMode.CEILING
    }

    private val targetRate = 1.5

    override suspend fun invoke(
        isOrigin: Boolean,
        value: String,
        currencyCode: String
    ): String {
        val exchangedCurrency = calculateValue(isOrigin, value, currencyCode)

        repository.saveTransaction(
            targetCurrencyCode = "UAH",
            originCurrencyCode = currencyCode,
            valueOrigin = value,
            exchangedCurrency
        )

        return exchangedCurrency
    }

    private fun calculateValue(
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

    private fun Double.round(): Double = formatter.format(this).toDouble()

    private fun getRate(currencyCode: String) = targetRate
}