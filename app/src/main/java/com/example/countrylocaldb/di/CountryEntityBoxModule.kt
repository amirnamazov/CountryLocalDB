package com.example.countrylocaldb.di

import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryEntityBoxModule {

    @Provides
    @Singleton
    fun providesCountryEntityBox(boxStore: BoxStore): Box<CountryEntity> =
        boxStore.boxFor(CountryEntity::class.java)
}