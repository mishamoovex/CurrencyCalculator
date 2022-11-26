package com.mykhailo.vasylenko.dispatchers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @DispatcherIo
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @DispatcherDefault
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DispatcherIo

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DispatcherDefault