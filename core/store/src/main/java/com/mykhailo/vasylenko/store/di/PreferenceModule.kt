package com.mykhailo.vasylenko.store.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.dataStoreFile
import com.mykhailo.vasylenko.dispatchers.DispatcherIo
import com.mykhailo.vasylenko.store.model.ExchangeCurrenciesPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object PreferenceModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext appContext: Context,
        @DispatcherIo dispatcher: CoroutineDispatcher,
        serializer: Serializer<ExchangeCurrenciesPreference>
    ): DataStore<ExchangeCurrenciesPreference> = DataStoreFactory.create(
        serializer = serializer,
        scope = CoroutineScope(SupervisorJob() + dispatcher)
    ) {
        appContext.dataStoreFile("user_preference.pb")
    }
}