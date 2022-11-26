package com.mykhailo.vasylenko.network.interceptors

import com.mykhailo.vasylenko.network.BuildConfig
import com.mykhailo.vasylenko.network.logger.ApiLogger
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LoggingInterceptor @Inject constructor() : Interceptor {

    private companion object {
        const val BUILD_TYPE_DEBUG = "debug"
    }

    private val httpLoggingInterceptor: HttpLoggingInterceptor? by lazy {
        if (BuildConfig.BUILD_TYPE == BUILD_TYPE_DEBUG) {
            HttpLoggingInterceptor(ApiLogger).apply {
                level = (HttpLoggingInterceptor.Level.BODY)
            }
        } else {
            null
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return httpLoggingInterceptor?.intercept(chain) ?: chain.proceed(chain.request())
    }
}