package com.example.countrylocaldb.domain.use_case

import com.example.countrylocaldb.common.ResourceState
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import com.example.countrylocaldb.data.data_source.remote.mapper.CountryEntitiesMapper.mapToCountryEntities
import com.example.countrylocaldb.domain.model.People
import com.example.countrylocaldb.domain.repository.PeopleListRepository
import io.objectbox.query.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleListUseCase @Inject constructor(private val repository: PeopleListRepository) {

    suspend fun getCountryList(): Flow<ResourceState> = flow {
        emit(ResourceState.Loading)
        val response = repository.getCountriesFromApi()
        val countryListDTO = response.body()
        if (response.isSuccessful) {
            if (countryListDTO == null) throw NullPointerException()
            handleSuccess(countryListDTO)
            emit(ResourceState.Success)
        } else {
            emit(ResourceState.Error)
        }
    }.catch { throwable ->
        emit(ResourceState.Error)
    }

    private suspend fun handleSuccess(countryListDTO: CountryListDTO) {
        val countryEntities = countryListDTO.mapToCountryEntities()
        repository.putCountriesToBox(countryEntities)
        setParamsToQueryPeople(repository.getAllHumanIds())
    }

    fun getQueryPeople(): Query<PeopleEntity> = repository.getQueryPeople()

    fun setParamsToQueryPeople(idArray: LongArray) = repository.setParamsToQueryPeople(idArray)

    fun mapToListPeople(entities: List<PeopleEntity> = getQueryPeople().find()): List<People> =
        entities.map {
            People(id = it.humanId, name = it.name, surname = it.surname)
        }
}