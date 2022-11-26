package com.mykhailo.vasylenko.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkStatDataItem(
    @Json(name = "cc")
    val currencyCode: String,
    @Json(name = "exchangedate")
    val exchangeDate: String,
    @Json(name = "r030")
    val code: Int,
    @Json(name = "rate")
    val rate: Double,
    @Json(name = "txt")
    val displayName: String
)