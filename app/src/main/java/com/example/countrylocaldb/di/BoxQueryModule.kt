package com.example.countrylocaldb.di

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CityEntity_
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity_
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.objectbox.Box
import io.objectbox.query.Query
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BoxQueryModule {

    @Provides
    @Singleton
    fun providesQueryCountry(box: Box<CountryEntity>): Query<CountryEntity> = box.query().build()

    @Provides
    @Singleton
    fun providesQueryCity(box: Box<CityEntity>, query: Query<CountryEntity>): Query<CityEntity> =
        box.query().`in`(CityEntity_.countryEntityId, query.findIds()).build()

    @Provides
    @Singleton
    fun providesQueryPeople(box: Box<PeopleEntity>, query: Query<CityEntity>): Query<PeopleEntity> =
        box.query().`in`(PeopleEntity_.cityEntityId, query.findIds()).build()
}