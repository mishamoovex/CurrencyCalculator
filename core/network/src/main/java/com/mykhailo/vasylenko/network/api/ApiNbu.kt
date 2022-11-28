package com.mykhailo.vasylenko.network.api

import com.mykhailo.vasylenko.network.model.NetworkStatDataItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNbu {

    @GET("v1/statdirectory/exchangenew?json")
    suspend fun getState(
        @Query("date") date: String?,
        @Query("valcode") currencyCode: String? = null
    ): List<NetworkStatDataItem>
}