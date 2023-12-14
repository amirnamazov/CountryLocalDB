package com.example.countrylocaldb.domain.repository

import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import retrofit2.Response

interface CountryListRepository {
    suspend fun getCountryList(): Response<CountryListDTO>
}