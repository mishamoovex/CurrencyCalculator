package com.mykhailo.vasylenko.features.calculator.domain

import com.mykhailo.vasylenko.features.calculator.data.history.ExchangeHistoryRepository
import com.mykhailo.vasylenko.features.calculator.data.stat.ExchangeStatRepository
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

internal class ConvertCurrencyUseCaseImpl @Inject constructor(
    private val historyRepository: ExchangeHistoryRepository,
    private val statRepository: ExchangeStatRepository
) : ConvertCurrencyUseCase {

    private val formatter = DecimalFormat("#.##").apply {
        roundingMode = RoundingMode.CEILING
    }

    override suspend fun invoke(
        isOrigin: Boolean,
        value: String,
        currencyCode: String
    ): String {
        val exchangedCurrency = calculateValue(isOrigin, value, currencyCode)

        historyRepository.saveTransaction(
            targetCurrencyCode = if (isOrigin) currencyCode else "UAH",
            originCurrencyCode = if (isOrigin) "UAH" else currencyCode,
            valueOrigin = value,
            exchangedCurrency
        )

        return exchangedCurrency
    }

    private suspend fun calculateValue(
        isOrigin: Boolean,
        value: String,
        currencyCode: String
    ): String {
        val currency = value.toDouble().round()
        val rate = statRepository.getRate(currencyCode)
        return if (isOrigin) {
            currency / rate
        } else {
            currency * rate
        }
            .round()
            .toString()
    }

    private fun Double.round(): Double = formatter.format(this).toDouble()
}