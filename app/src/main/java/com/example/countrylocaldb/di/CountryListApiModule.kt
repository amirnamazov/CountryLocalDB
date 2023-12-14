package com.example.countrylocaldb.di

import com.example.countrylocaldb.data.data_source.remote.api.CountryListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryListApiModule {
    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): CountryListApi =
        retrofit.create(CountryListApi::class.java)
}