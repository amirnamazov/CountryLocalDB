package com.example.countrylocaldb.domain.use_case

import com.example.countrylocaldb.common.ResourceState
import com.example.countrylocaldb.data.data_source.remote.mapper.CountryEntitiesMapper.mapToCountryEntities
import com.example.countrylocaldb.domain.repository.CountryListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryListUseCase @Inject constructor(private val repository: CountryListRepository) {

    suspend fun getCountryList(): Flow<ResourceState> = flow {
        emit(ResourceState.Loading)
        val response = repository.getCountryList()
        val countryListDTO = response.body()
        if (response.isSuccessful) {
            if (countryListDTO == null) throw NullPointerException()
            repository.putCountriesToBox(countryListDTO.mapToCountryEntities())
            emit(ResourceState.Success)
        } else {
            emit(ResourceState.Error)
        }
    }.catch { throwable ->
        emit(ResourceState.Error)
    }

    fun getQueryCountry() = repository.getQueryCountry()
}