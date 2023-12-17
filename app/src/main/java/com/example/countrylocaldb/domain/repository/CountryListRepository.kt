package com.example.countrylocaldb.domain.repository

import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import io.objectbox.query.Query
import retrofit2.Response

interface CountryListRepository {
    suspend fun getCountryList(): Response<CountryListDTO>

    fun putCountriesToBox(entities: List<PeopleEntity>)

    fun getQuery(): Query<PeopleEntity>
}