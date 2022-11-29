package com.mykhailo.vasylenko.common.tests.extensions

import com.mykhailo.vasylenko.common.data.NoInternetExceptionProvider
import com.mykhailo.vasylenko.common.data.UnknownExceptionProvider
import com.mykhailo.vasylenko.common.exeption.isNoInternetException
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Check if an Exception belongs to NoInternetConnection group")
internal class NoInternetExtensionTest {

    @ParameterizedTest(name = "when invoked on {0}")
    @ArgumentsSource(NoInternetExceptionProvider::class)
    fun `should return true`(exception: Exception) {
        //Given an exception that belongs to the NoInternetConnection group
        //When verifying this exception
        val isNoInternetException = exception.isNoInternetException()
        //Than the result should be "true"
        isNoInternetException.shouldBeTrue()
    }

    @ParameterizedTest(name = "when invoked on {0}")
    @ArgumentsSource(UnknownExceptionProvider::class)
    fun `should return false`(exception: Exception) {
        //Given an exception that doesn't belongs to the NoInternetConnection group
        //When verifying this exception
        val isNoInternetException = exception.isNoInternetException()
        //Than the result should be "false"
        isNoInternetException.shouldBeFalse()
    }

}