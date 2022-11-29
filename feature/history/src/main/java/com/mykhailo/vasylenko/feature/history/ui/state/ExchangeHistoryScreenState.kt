package com.mykhailo.vasylenko.feature.history.ui.state

import com.mykhailo.vasylenko.common.state.MessageState
import com.mykhailo.vasylenko.feature.history.domain.ExchangeTransaction

data class ExchangeHistoryScreenState(
    val messageState: MessageState,
    val currencies: List<ExchangeTransaction>?
)
