package com.mykhailo.vasylenko.store.serializer

import androidx.datastore.core.Serializer
import com.mykhailo.vasylenko.dispatchers.DispatcherIo
import com.mykhailo.vasylenko.store.model.ExchangeCurrenciesPreference
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

internal class PreferenceSerializer @Inject constructor(
    @DispatcherIo private val dispatcher: CoroutineDispatcher,
) : Serializer<ExchangeCurrenciesPreference> {

    override val defaultValue: ExchangeCurrenciesPreference
        get() = ExchangeCurrenciesPreference(
            list = emptyList()
        )

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(t: ExchangeCurrenciesPreference, output: OutputStream) {
        withContext(dispatcher) {
            output.write(
                Json.encodeToString(
                    serializer = ExchangeCurrenciesPreference.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }

    override suspend fun readFrom(input: InputStream): ExchangeCurrenciesPreference = try {
        Json.decodeFromString(
            deserializer = ExchangeCurrenciesPreference.serializer(),
            string = input.readBytes().decodeToString()
        )
    } catch (e: Exception) {
        defaultValue
    }
}