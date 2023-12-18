package com.example.countrylocaldb.domain.repository

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import io.objectbox.query.Query
import retrofit2.Response

interface PeopleListRepository {

    fun isLocalDbEmpty(): Boolean

    suspend fun getCountriesFromApi(): Response<CountryListDTO>

    suspend fun putCountriesToBox(entities: List<CountryEntity>)

    fun getQueryCity(): Query<CityEntity>

    fun clearAllBoxes()

    fun publishAllCities()
}