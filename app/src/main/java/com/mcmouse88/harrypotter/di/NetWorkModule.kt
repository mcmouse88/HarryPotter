package com.mcmouse88.harrypotter.di

import com.mcmouse88.harrypotter.data.network.ApiService
import com.mcmouse88.harrypotter.domain.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
class NetWorkModule {

    @[Provides Singleton]
    fun provideMoshi(): Moshi {
       return Moshi.Builder()
           .addLast(KotlinJsonAdapterFactory())
           .build()
    }

    @[Provides MoshiFactory]
    fun provideMoshiConverterFactory(
        moshi: Moshi
    ): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    @[Provides Singleton GsonFactory]
    fun provideGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @[Provides Singleton LoggingInterceptor]
    fun provideLoggerInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @[Provides Singleton]
    fun provideOkHttpClient(
        @LoggingInterceptor interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .build()
    }

    @[Provides Singleton]
    fun provideApiService(
        @GsonFactory factory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(factory)
            .build()
            .create()
    }
}

@[Qualifier Retention]
annotation class LoggingInterceptor

@[Qualifier Retention]
annotation class MoshiFactory

@[Qualifier Retention]
annotation class GsonFactory
