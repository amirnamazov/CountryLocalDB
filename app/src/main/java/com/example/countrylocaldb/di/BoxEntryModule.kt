package com.example.countrylocaldb.di

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BoxEntryModule {

    @Provides
    @Singleton
    fun providesCountryEntityBox(boxStore: BoxStore): Box<CountryEntity> =
        boxStore.boxFor(CountryEntity::class.java)

    @Provides
    @Singleton
    fun providesCityEntityBox(boxStore: BoxStore): Box<CityEntity> =
        boxStore.boxFor(CityEntity::class.java)

    @Provides
    @Singleton
    fun providesPeopleEntityBox(boxStore: BoxStore): Box<PeopleEntity> =
        boxStore.boxFor(PeopleEntity::class.java)
}