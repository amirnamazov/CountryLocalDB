package com.example.countrylocaldb.di

import com.example.countrylocaldb.data.repository.FilterRepositoryImpl
import com.example.countrylocaldb.data.repository.PeopleListRepositoryImpl
import com.example.countrylocaldb.domain.repository.FilterRepository
import com.example.countrylocaldb.domain.repository.PeopleListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MainRepositoryModule {
    @Binds
    fun bindPeopleListRepository(repository: PeopleListRepositoryImpl): PeopleListRepository

    @Binds
    fun bindFilterRepository(repository: FilterRepositoryImpl): FilterRepository
}