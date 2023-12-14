package com.example.countrylocaldb.data.repository

import com.example.countrylocaldb.data.data_source.remote.api.CountryListApi
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import com.example.countrylocaldb.domain.repository.CountryListRepository
import retrofit2.Response
import javax.inject.Inject

class CountryListRepositoryImpl @Inject constructor(private val api: CountryListApi) :
    CountryListRepository {

    override suspend fun getCountryList(): Response<CountryListDTO> = api.getData()
}