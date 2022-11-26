package com.mykhailo.vasylenko.common.state

import com.mykhailo.vasylenko.common.exeption.SnackbarMessage

data class MessageState(
    val message: SnackbarMessage? = null,
    val onMessageShowed: () -> Unit
)
