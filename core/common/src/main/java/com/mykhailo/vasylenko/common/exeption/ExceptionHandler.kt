package com.mykhailo.vasylenko.common.exeption

import com.mykhailo.vasylenko.common.R
import java.util.*

object ExceptionHandler {

    private var unknownErrorMessage = "Unknown error"

    fun setUnknownErrorMessage(message: String) {
        unknownErrorMessage = message
    }

    fun errorToMessage(
        e: Exception?,
        id: Long = UUID.randomUUID().mostSignificantBits
    ): SnackbarMessage = if (e?.isNoInternetException() == true) {
        SnackbarMessage(
            id = id,
            templateRes = R.string.msg_error_no_internet
        )
    } else {
        val placeholders = e?.message ?: unknownErrorMessage
        SnackbarMessage(
            id = id,
            templateRes = R.string.msg_unknown_error,
            placeholders = arrayOf(placeholders)
        )
    }
}