package com.example.countrylocaldb.data.data_source.remote.api

import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import retrofit2.Response
import retrofit2.http.GET

interface CountryListApi {
    @GET("getdata")
    suspend fun getData(): Response<CountryListDTO>
}