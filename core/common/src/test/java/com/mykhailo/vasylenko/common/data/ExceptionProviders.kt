package com.mykhailo.vasylenko.common.data

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import java.util.stream.Stream

internal object NoInternetExceptionProvider : org.junit.jupiter.params.provider.ArgumentsProvider {
    override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> =
        Stream.of(
            Arguments.of(TimeoutException()),
            Arguments.of(UnknownHostException()),
            Arguments.of(ConnectException()),
        )
}

internal object UnknownExceptionProvider : org.junit.jupiter.params.provider.ArgumentsProvider {
    override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> =
        Stream.of(
            Arguments.of(IllegalArgumentException()),
            Arguments.of(NullPointerException()),
            Arguments.of(Exception()),
        )
}