package com.mykhailo.vasylenko.features.calculator.ui.state

data class ExchangeItemState(
    val value: String,
    val onValueChanged: (String) -> Unit,
    val currency: String?,
    val currencyCode: String?,
    val isLoading: Boolean,
    val isFieldEnabled: Boolean,
    val buttonTitle: String
)