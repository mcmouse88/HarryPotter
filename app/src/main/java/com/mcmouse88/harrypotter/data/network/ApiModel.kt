package com.mcmouse88.harrypotter.data.network

import com.mcmouse88.harrypotter.domain.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApiModel {

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    @Provides
    fun retrofit(): ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ApiService::class.java)

}