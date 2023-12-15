package com.example.countrylocaldb.data.repository

import com.example.countrylocaldb.common.ResourceState
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.mapper.CountryListMapper.mapToCountryList
import com.example.countrylocaldb.data.data_source.remote.api.CountryListApi
import com.example.countrylocaldb.data.data_source.remote.mapper.CountryEntitiesMapper.mapToCountryEntities
import com.example.countrylocaldb.domain.model.Country
import com.example.countrylocaldb.domain.repository.CountryListRepository
import io.objectbox.Box
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryListRepositoryImpl @Inject constructor(
    private val api: CountryListApi,
    private val box: Box<CountryEntity>
) : CountryListRepository {

    override suspend fun getCountriesRemote(): Flow<ResourceState> = flow {
        emit(ResourceState.Loading)
        val response = api.getData()
        val countryListDTO = response.body()
        if (response.isSuccessful) {
            if (countryListDTO == null) throw NullPointerException()
            countryListDTO.mapToCountryEntities().forEach { box.put(it) }
            emit(ResourceState.Success)
        } else {
            emit(ResourceState.Error)
        }
    }.catch { throwable ->
        emit(ResourceState.Error)
    }

    override suspend fun getCountriesLocal(): List<Country> = box.all.mapToCountryList()
}