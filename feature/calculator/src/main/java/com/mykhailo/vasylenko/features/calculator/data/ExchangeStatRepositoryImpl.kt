package com.mykhailo.vasylenko.features.calculator.data

import com.mykhailo.vasylenko.features.calculator.domain.model.ExchangeStat
import com.mykhailo.vasylenko.network.api.ApiNbu
import com.mykhailo.vasylenko.network.model.NetworkStatDataItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ExchangeStatRepositoryImpl @Inject constructor(
    private val api: ApiNbu
) : ExchangeStatRepository {

    override suspend fun getExchangeStats(
        currencyCode: String?,
        date: LocalDateTime
    ): List<ExchangeStat> =
        api.getState(
            date = date.toRequestFormat(),
            currencyCode = currencyCode
        ).map {
            it.toExchangeStat()
        }

    private fun LocalDateTime.toRequestFormat(): String =
        this.format(DateTimeFormatter.ofPattern("yyyyMMdd"))

    private fun NetworkStatDataItem.toExchangeStat() =
        ExchangeStat(
            name = displayName,
            code = currencyCode,
            rate = rate
        )
}