package com.example.countrylocaldb.domain.use_case

import com.example.countrylocaldb.common.NetworkUtils
import com.example.countrylocaldb.common.ResourceState
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import com.example.countrylocaldb.data.data_source.remote.mapper.CountryEntitiesMapper.mapToCountryEntities
import com.example.countrylocaldb.domain.repository.PeopleListRepository
import io.objectbox.query.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.net.ConnectException
import javax.inject.Inject

class PeopleListUseCase @Inject constructor(
    private val repository: PeopleListRepository,
    private val networkUtils: NetworkUtils
) {

    fun isLocalDbEmpty(): Boolean = repository.isLocalDbEmpty()

    suspend fun getCountryList(): Flow<ResourceState<CountryListDTO>> = flow {
        if (!networkUtils.isNetworkAvailable()) throw ConnectException("No connection.")
        emit(ResourceState.Loading())
        val response = repository.getCountriesFromApi()
        val countryListDTO = response.body()
        if (response.isSuccessful) {
            if (countryListDTO == null) throw NullPointerException()
            emit(ResourceState.Success(countryListDTO))
        } else {
            emit(ResourceState.Error(response.message()))
        }
    }.catch { throwable ->
        emit(ResourceState.Error(throwable.message ?: "Something went wrong."))
    }

    suspend fun handleSuccess(countryListDTO: CountryListDTO) = with(repository) {
        clearAllBoxes()
        putCountriesToBox(countryListDTO.mapToCountryEntities())
        publishAllCitiesAndPeople()
    }

    fun reloadLocalDb() = repository.publishAllCitiesAndPeople()

    fun getQueryPeople(): Query<PeopleEntity> = repository.getQueryPeople()
}