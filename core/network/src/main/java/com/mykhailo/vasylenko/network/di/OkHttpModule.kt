package com.mykhailo.vasylenko.network.di

import com.mykhailo.vasylenko.network.interceptors.LoggingInterceptor
import com.mykhailo.vasylenko.network.qualifiers.UnauthorizedClient
import com.mykhailo.vasylenko.network.util.setTimeout
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object OkHttpModule {

    @Singleton
    @Provides
    @UnauthorizedClient
    fun provideUnauthorizedOkHttpClient(
        loggingInterceptor: LoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor(loggingInterceptor)
            setTimeout()
        }
        .build()

}