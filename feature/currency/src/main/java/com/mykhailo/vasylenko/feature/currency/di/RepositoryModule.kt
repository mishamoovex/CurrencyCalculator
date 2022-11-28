package com.mykhailo.vasylenko.feature.currency.di

import com.mykhailo.vasylenko.feature.currency.data.CurrencyRepository
import com.mykhailo.vasylenko.feature.currency.data.CurrencyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindRepository(impl: CurrencyRepositoryImpl): CurrencyRepository
}