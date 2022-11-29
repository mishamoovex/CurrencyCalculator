package com.mykhailo.vasylenko.core.db.di

import com.mykhailo.vasylenko.core.db.data_store.history.ExchangeHistoryDataStore
import com.mykhailo.vasylenko.core.db.data_store.history.ExchangeHistoryDataStoreImpl
import com.mykhailo.vasylenko.core.db.data_store.rates.ExchangeRateDataStore
import com.mykhailo.vasylenko.core.db.data_store.rates.ExchangeRateDataStoreImpl
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
    fun bindHistoryDataStore(impl: ExchangeHistoryDataStoreImpl): ExchangeHistoryDataStore

    @Binds
    fun bindRateDataStore(impl: ExchangeRateDataStoreImpl): ExchangeRateDataStore
}