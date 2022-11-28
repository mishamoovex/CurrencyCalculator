package com.mykhailo.vasylenko.features.calculator.di

import com.mykhailo.vasylenko.features.calculator.domain.ConvertCurrencyUseCase
import com.mykhailo.vasylenko.features.calculator.domain.ConvertCurrencyUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindUseCase(impl: ConvertCurrencyUseCaseImpl): ConvertCurrencyUseCase
}