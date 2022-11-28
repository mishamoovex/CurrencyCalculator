package com.mykhailo.vasylenko.features.calculator.data

import com.mykhailo.vasylenko.network.api.ApiNbu
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ExchangeStatRepositoryImpl @Inject constructor(
    private val api: ApiNbu
) : ExchangeStatRepository {

    override suspend fun loadStat(
        date: LocalDate?
    ){
//        api.getState(
//            date = date?.toRequestFormat()
//        ).map {
//            it.toExchangeStat()
//        }
    }

    private fun LocalDate.toRequestFormat(): String =
        this.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
}