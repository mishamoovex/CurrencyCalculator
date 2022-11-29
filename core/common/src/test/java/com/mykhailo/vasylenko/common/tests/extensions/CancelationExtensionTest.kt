package com.mykhailo.vasylenko.common.tests.extensions

import com.mykhailo.vasylenko.common.data.UnknownExceptionProvider
import com.mykhailo.vasylenko.common.exeption.isCoroutineCancellation
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import kotlin.coroutines.cancellation.CancellationException

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Check if an Exception is CoroutineCancelation exception")
internal class CancelationExtensionTest {

    @Test
    fun `should return true when invoked on the CancelationException`() {
        //Given the Cancelation exception
        val exception = CancellationException()
        //When verifying this exception
        val isCancelationException = exception.isCoroutineCancellation()
        //Than the result should be "true"
        isCancelationException.shouldBeTrue()
    }

    @ParameterizedTest(name = "when invoked on {0}")
    @ArgumentsSource(UnknownExceptionProvider::class)
    fun `should return false`(exception: Exception) {
        //Given an exception that doesn't the Cancelation exception
        //When verifying this exception
        val isCancelationException = exception.isCoroutineCancellation()
        //Than the result should be "false"
        isCancelationException.shouldBeFalse()
    }

}