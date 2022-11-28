package com.mykhailo.vasylenko.features.calculator.ui.state

data class ExchangeCardState(
    val itemOriginal: ExchangeItemState,
    val itemTarget: ExchangeItemState,
    val showTargetCurrencyField: Boolean
)
