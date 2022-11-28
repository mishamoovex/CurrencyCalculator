package com.mykhailo.vasylenko.features.calculator.ui.state

import com.mykhailo.vasylenko.common.state.MessageState

data class CalculatorScreenState(
    val messageState: MessageState,
    val cardState: ExchangeCardState,
    val dateState: DateState,
    val showCurrencyCalculator: Boolean
)
