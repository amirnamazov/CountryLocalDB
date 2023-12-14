package com.example.countrylocaldb.di

import com.example.countrylocaldb.common.Constants.baseUrl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providesFactory(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder().setLenient().create()
    )

    @Provides
    @Singleton
    fun providesInstance(factory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(factory)
            .baseUrl(baseUrl)
            .build()
}