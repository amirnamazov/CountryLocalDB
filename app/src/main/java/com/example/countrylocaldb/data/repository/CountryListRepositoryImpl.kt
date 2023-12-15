package com.example.countrylocaldb.data.repository

import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.remote.api.CountryListApi
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import com.example.countrylocaldb.domain.repository.CountryListRepository
import io.objectbox.Box
import io.objectbox.kotlin.query
import io.objectbox.query.Query
import retrofit2.Response
import javax.inject.Inject

class CountryListRepositoryImpl @Inject constructor(
    private val api: CountryListApi,
    private val box: Box<CountryEntity>
) : CountryListRepository {

    override suspend fun getCountryList(): Response<CountryListDTO> = api.getData()

    override fun putCountriesToBox(entities: List<CountryEntity>) = box.put(entities)

    override fun getQueryCountry(): Query<CountryEntity> = box.query {}
}