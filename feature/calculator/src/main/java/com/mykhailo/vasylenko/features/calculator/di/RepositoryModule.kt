package com.mykhailo.vasylenko.features.calculator.di

import com.mykhailo.vasylenko.features.calculator.data.ExchangeStatRepository
import com.mykhailo.vasylenko.features.calculator.data.ExchangeStatRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindExchangeRepository(impl: ExchangeStatRepositoryImpl): ExchangeStatRepository
}