package com.mykhailo.vasylenko.network.di

import com.mykhailo.vasylenko.network.api.ApiNbu
import com.mykhailo.vasylenko.network.qualifiers.UnauthorizedClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApi(
        @UnauthorizedClient retrofit: Retrofit
    ): ApiNbu = retrofit.create()
}