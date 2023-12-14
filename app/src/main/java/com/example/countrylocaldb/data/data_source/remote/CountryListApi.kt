package com.example.countrylocaldb.data.data_source.remote

import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import retrofit2.Response
import retrofit2.http.GET

interface CountryListApi {
    @GET("getdata")
    fun getData(): Response<CountryListDTO>
}