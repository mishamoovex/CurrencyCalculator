package com.mykhailo.vasylenko.core.db.di

import com.mykhailo.vasylenko.core.db.data_store.ExchangeHistoryDataStore
import com.mykhailo.vasylenko.core.db.data_store.ExchangeHistoryDataStoreImpl
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
}