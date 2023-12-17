package com.example.countrylocaldb.domain.repository

import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import io.objectbox.query.Query
import retrofit2.Response

interface CountryListRepository {

    suspend fun getCountriesFromApi(): Response<CountryListDTO>

    suspend fun putCountriesToBox(entities: List<CountryEntity>)

//    suspend fun putPeopleToBox(entities: List<PeopleEntity>)

//    fun getQueryCountries(): Query<CountryEntity>
//
    fun getQueryPeople(): Query<PeopleEntity>

    fun setParamsToQueryPeople(idArray: LongArray)

    fun getAllHumanIds(): LongArray
}