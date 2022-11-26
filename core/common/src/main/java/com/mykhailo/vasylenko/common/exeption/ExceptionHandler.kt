package com.mykhailo.vasylenko.common.exeption

import com.mykhailo.vasylenko.common.R

object ExceptionHandler {

    fun errorToMessage(e: Exception?): com.mykhailo.vasylenko.common.messaging.SnackbarMessage =
        if (e?.isNoInternetException() == true) {
            com.mykhailo.vasylenko.common.messaging.SnackbarMessage(R.string.msg_error_no_internet)
        } else {
            val placeholders = e?.message ?: "Unknown error"
            com.mykhailo.vasylenko.common.messaging.SnackbarMessage(
                R.string.msg_unknown_error,
                arrayOf(placeholders)
            )
        }
}