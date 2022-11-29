package com.mykhailo.vasylenko.common.tests.util

import com.mykhailo.vasylenko.common.R
import com.mykhailo.vasylenko.common.data.NoInternetExceptionProvider
import com.mykhailo.vasylenko.common.data.UnknownExceptionProvider
import com.mykhailo.vasylenko.common.exeption.ExceptionHandler
import com.mykhailo.vasylenko.common.exeption.SnackbarMessage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Generate SnackbarMessage from Exception")
internal class ExceptionHandlerTest {

    @ParameterizedTest(name = "when invoked on {0}")
    @ArgumentsSource(NoInternetExceptionProvider::class)
    fun `should return no internet connection template`(exception: Exception) {
        //When generating a SnackbarMessage on the exception
        //from the NoInternetConnection group
        val actualMessage = ExceptionHandler.errorToMessage(
            e = exception,
            id = 0
        )
        //Than the generated message should be equal to expected message
        val expectedMessage = SnackbarMessage(
            id = 0,
            templateRes = R.string.msg_error_no_internet
        )
        actualMessage shouldBe expectedMessage
    }

    @ParameterizedTest(name = "when invoked on {0}")
    @ArgumentsSource(UnknownExceptionProvider::class)
    fun `should return unknown error template with ExceptionHandler placeholder message`(exception: Exception) {
        //Given the ExceptionHandler with custom errorPlaceholder message
        val placeholderMessage = "unknown error message"
        val messageHandler = ExceptionHandler.apply {
            setUnknownErrorMessage(placeholderMessage)
        }
        //When generating a SnackbarMessage on the exception
        //that doesn't contain message field
        val actualMessage = messageHandler.errorToMessage(
            e = exception,
            id = 0
        )
        //Than the generated message should be equal to expected message
        val expectedMessage = SnackbarMessage(
            id = 0,
            templateRes = R.string.msg_unknown_error,
            placeholders = arrayOf(placeholderMessage)
        )
        actualMessage shouldBe expectedMessage
    }

    @Test
    fun `should return unknown error template with error message`() {
        //Given an exception that doesn't belongs to the NoInternetConnectionGroup
        //and has the error field
        val errorMessage = "error message"
        val exception = IllegalArgumentException(errorMessage)
        //When generating a SnackbarMessage from the given exception
        val actualMessage = ExceptionHandler.errorToMessage(
            e = exception,
            id = 0
        )
        //Than the generated message should be equal to expected message
        val expectedMessage = SnackbarMessage(
            id = 0,
            templateRes = R.string.msg_unknown_error,
            placeholders = arrayOf(errorMessage)
        )
        actualMessage shouldBe expectedMessage
    }

}