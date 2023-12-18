package com.example.countrylocaldb.di

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CityEntity_
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity_
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
    fun providesQueryCountry(box: Box<CountryEntity>): Query<CountryEntity> =
        box.query().`in`(CountryEntity_.countryId, box.all.map { it.countryId }.toLongArray())
            .build()

    @Provides
    @Singleton
    fun providesQueryCity(box: Box<CityEntity>): Query<CityEntity> =
        box.query().`in`(CityEntity_.cityId, box.all.map { it.cityId }.toLongArray()).build()
}