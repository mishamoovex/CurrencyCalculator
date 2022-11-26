package com.mykhailo.vasylenko.app.di

import com.mykhailo.vasylenko.app.data.currency.ExchangeCurrenciesRepository
import com.mykhailo.vasylenko.app.data.currency.ExchangeCurrenciesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindExchangeRepository(impl: ExchangeCurrenciesRepositoryImpl): ExchangeCurrenciesRepository
}