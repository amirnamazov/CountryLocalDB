package com.example.countrylocaldb.domain.use_case

import com.example.countrylocaldb.common.ResourceState
import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.mapper.CountryListMapper.mapToPeopleList
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import com.example.countrylocaldb.data.data_source.remote.mapper.CountryEntitiesMapper.mapToCountryEntities
import com.example.countrylocaldb.domain.repository.PeopleListRepository
import io.objectbox.query.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleListUseCase @Inject constructor(private val repository: PeopleListRepository) {

    fun isLocalDbEmpty(): Boolean = repository.isLocalDbEmpty()

    suspend fun getCountryList(): Flow<ResourceState> = flow {
        emit(ResourceState.Loading())
        val response = repository.getCountriesFromApi()
        val countryListDTO = response.body()
        if (response.isSuccessful) {
            if (countryListDTO == null) throw NullPointerException()
            handleSuccess(countryListDTO)
            emit(ResourceState.Success())
        } else {
            emit(ResourceState.Error(response.message()))
        }
    }.catch { throwable ->
        emit(ResourceState.Error(throwable.message ?: "Something went wrong."))
    }

    private suspend fun handleSuccess(countryListDTO: CountryListDTO) = with(repository) {
        clearAllBoxes()
        val countryEntities = countryListDTO.mapToCountryEntities()
        putCountriesToBox(countryEntities)
        publishAllCities()
    }

    fun getQueryCity(): Query<CityEntity> = repository.getQueryCity()

    fun flatMapToPeopleList(entities: List<CityEntity>) =
        entities.flatMap { city -> city.peopleList.mapToPeopleList() }
}