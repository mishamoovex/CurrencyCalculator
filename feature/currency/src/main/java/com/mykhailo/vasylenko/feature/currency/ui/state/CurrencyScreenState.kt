package com.mykhailo.vasylenko.feature.currency.ui.state

import com.mykhailo.vasylenko.common.state.MessageState
import com.mykhailo.vasylenko.core.models.ExchangeItemType
import com.mykhailo.vasylenko.feature.currency.domain.ExchangeCurrency

data class CurrencyScreenState(
    val messageState: MessageState,
    val currencies: List<ExchangeCurrency>,
    val type: ExchangeItemType
)
