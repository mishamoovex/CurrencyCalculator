package com.mykhailo.vasylenko.store.di

import com.mykhailo.vasylenko.store.datastore.ExchangeCurrencyDataStore
import com.mykhailo.vasylenko.store.datastore.ExchangeCurrencyDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataStoreModule {

    @Binds
    @Singleton
    fun bindDataStore(impl: ExchangeCurrencyDataStoreImpl): ExchangeCurrencyDataStore

}