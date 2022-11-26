package com.mykhailo.vasylenko.store.di

import androidx.datastore.core.Serializer
import com.mykhailo.vasylenko.store.model.ExchangeCurrenciesPreference
import com.mykhailo.vasylenko.store.serializer.PreferenceSerializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface SerializerModule {

    @Binds
    @Singleton
    fun bindSerializer(impl: PreferenceSerializer): Serializer<ExchangeCurrenciesPreference>
}