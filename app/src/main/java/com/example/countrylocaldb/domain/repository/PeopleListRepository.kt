package com.example.countrylocaldb.domain.repository

import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import io.objectbox.query.Query
import retrofit2.Response

interface PeopleListRepository {

    suspend fun getCountriesFromApi(): Response<CountryListDTO>

    suspend fun putCountriesToBox(entities: List<CountryEntity>)

    fun getQueryPeople(): Query<PeopleEntity>
}