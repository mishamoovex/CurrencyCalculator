package com.mykhailo.vasylenko.feature.history.di

import com.mykhailo.vasylenko.feature.history.data.ExchangeHistoryRepository
import com.mykhailo.vasylenko.feature.history.data.ExchangeHistoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindRepository(impl: ExchangeHistoryRepositoryImpl): ExchangeHistoryRepository
}