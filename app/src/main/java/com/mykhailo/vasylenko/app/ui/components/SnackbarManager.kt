package com.mykhailo.vasylenko.app.ui.components

import com.mykhailo.vasylenko.common.exeption.SnackbarMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Class responsible for managing Snackbar messages to show on the screen
 */
class SnackbarManager {

    private val _messages: MutableStateFlow<List<SnackbarMessage>> = MutableStateFlow(emptyList())
    val messages = _messages.asStateFlow()

    fun showMessage(message: SnackbarMessage) {
        _messages.update { currentMessages -> currentMessages + message }
    }

    fun setMessageShown(messageId: Long) {
        _messages.update { currentMessages ->
            currentMessages.filterNot { it.id == messageId }
        }
    }
}