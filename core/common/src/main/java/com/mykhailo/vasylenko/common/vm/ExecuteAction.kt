package com.mykhailo.vasylenko.common.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mykhailo.vasylenko.common.exeption.ExceptionHandler
import com.mykhailo.vasylenko.common.exeption.isCoroutineCancellation
import com.mykhailo.vasylenko.common.messaging.SnackbarMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun <T : ViewModel> T.executeAction(
    onLoading: suspend CoroutineScope.() -> Unit = {},
    onError: suspend CoroutineScope.(SnackbarMessage) -> Unit = {},
    toDo: suspend CoroutineScope.() -> Unit
): Job = viewModelScope.launch {
    try {
        onLoading.invoke(this)
        toDo.invoke(this)
    } catch (e: Exception) {
        if (e.isCoroutineCancellation()) {
            /*
            * Propagates cancellation exception to the parent coroutine scope
            * to be able to cancel the current request while a user left a screen
            * when we do not need the execution result anymore
            * */
            throw e
        } else {
            val message = ExceptionHandler.errorToMessage(e)
            onError.invoke(this, message)
        }
    }
}