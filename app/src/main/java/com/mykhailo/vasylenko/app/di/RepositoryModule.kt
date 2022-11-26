package com.mykhailo.vasylenko.app.di

import com.mykhailo.vasylenko.app.data.ExchangeCurrenciesRepository
import com.mykhailo.vasylenko.app.data.ExchangeCurrenciesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindExchangeRepository(impl: ExchangeCurrenciesRepositoryImpl): ExchangeCurrenciesRepository
}