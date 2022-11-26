package com.mykhailo.vasylenko.app.ui.state

import com.mykhailo.vasylenko.common.state.MessageState

data class MainScreenState(
    val messageState: MessageState,
    val appStartupCompleted: Boolean
)
