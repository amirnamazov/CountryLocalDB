package com.example.countrylocaldb.di

import com.example.countrylocaldb.data.repository.PeopleListRepositoryImpl
import com.example.countrylocaldb.domain.repository.PeopleListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
fun interface MainRepositoryModule {
    @Binds
    fun bindRepository(repository: PeopleListRepositoryImpl): PeopleListRepository
}