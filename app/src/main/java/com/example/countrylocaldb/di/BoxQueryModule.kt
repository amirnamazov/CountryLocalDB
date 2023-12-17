package com.example.countrylocaldb.di

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
    fun providesQueryPeople(box: Box<PeopleEntity>): Query<PeopleEntity> =
        box.query().`in`(PeopleEntity_.humanId, box.all.map { it.humanId }.toLongArray()).build()
}