package com.mykhailo.vasylenko.features.calculator.ui.state

import com.mykhailo.vasylenko.common.state.MessageState
import com.mykhailo.vasylenko.core.models.ExchangeItemSelectionResult

data class CalculatorScreenState(
    val messageState: MessageState,
    val cardState: ExchangeCardState,
    val dateState: DateState,
    val showCurrencyCalculator: Boolean,
    val onCurrencySelected: (ExchangeItemSelectionResult) -> Unit
)
