package com.mykhailo.vasylenko.features.calculator.di

import com.mykhailo.vasylenko.features.calculator.data.history.ExchangeHistoryRepository
import com.mykhailo.vasylenko.features.calculator.data.history.ExchangeHistoryRepositoryImpl
import com.mykhailo.vasylenko.features.calculator.data.stat.ExchangeStatRepository
import com.mykhailo.vasylenko.features.calculator.data.stat.ExchangeStatRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindExchangeRepository(impl: ExchangeStatRepositoryImpl): ExchangeStatRepository

    @Binds
    fun bindHistoryRepository(impl: ExchangeHistoryRepositoryImpl): ExchangeHistoryRepository
}