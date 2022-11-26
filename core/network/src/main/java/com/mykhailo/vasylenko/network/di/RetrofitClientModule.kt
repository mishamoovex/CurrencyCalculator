package com.mykhailo.vasylenko.network.di

import com.mykhailo.vasylenko.network.BuildConfig
import com.mykhailo.vasylenko.network.qualifiers.UnauthorizedClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitClientModule {

    @Provides
    @Singleton
    fun provideMoshiAdapter(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    @UnauthorizedClient
    fun provideUnauthorizedRetrofitClient(
        moshi: Moshi,
        @UnauthorizedClient okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BuildConfig.SERVER_URL)
        .client(okHttpClient)
        .build()
}