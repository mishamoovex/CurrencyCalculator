package com.mykhailo.vasylenko.core.models

data class ExchangeItemSelectionResult(
    val type: ExchangeItemType,
    val code: String,
    val name: String
): java.io.Serializable
