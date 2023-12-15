package com.example.countrylocaldb.data.repository

import com.example.countrylocaldb.common.ResourceState
import com.example.countrylocaldb.data.data_source.remote.api.CountryListApi
import com.example.countrylocaldb.data.data_source.remote.mapper.CountryListMapper.mapToCountryEntities
import com.example.countrylocaldb.domain.repository.CountryListRepository
import io.objectbox.BoxStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryListRepositoryImpl @Inject constructor(
    private val api: CountryListApi,
    private val boxStore: BoxStore
) : CountryListRepository {

    override suspend fun getCountryList(): Flow<ResourceState<String>> = flow {
        emit(ResourceState.Loading())
        val response = api.getData()
        val countryListDTO = response.body()
        if (response.isSuccessful && countryListDTO != null) {
            val countryEntities = countryListDTO.mapToCountryEntities(boxStore)
            emit(ResourceState.Success(""))
        } else {
            emit(ResourceState.Error(response.errorBody()?.string()))
        }
    }.catch { throwable ->
        emit(ResourceState.Error(throwable.message))
    }
}