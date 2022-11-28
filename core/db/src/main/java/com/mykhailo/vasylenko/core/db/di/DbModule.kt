package com.mykhailo.vasylenko.core.db.di

import android.content.Context
import androidx.room.Room
import com.mykhailo.vasylenko.core.db.db.AppDatabase
import com.mykhailo.vasylenko.core.db.db.ExchangeHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DbModule {

    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext appContext: Context
    ): AppDatabase = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "main-database"
    ).build()

    @Provides
    @Singleton
    fun provideExchangeHistoryDao(
        db: AppDatabase
    ): ExchangeHistoryDao = db.userDao()
}