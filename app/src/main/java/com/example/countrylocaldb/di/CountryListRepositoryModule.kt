package com.example.countrylocaldb.di

import com.example.countrylocaldb.data.repository.CountryListRepositoryImpl
import com.example.countrylocaldb.domain.repository.CountryListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
fun interface CountryListRepositoryModule {
    @Binds
    fun bindRepository(repository: CountryListRepositoryImpl): CountryListRepository
}