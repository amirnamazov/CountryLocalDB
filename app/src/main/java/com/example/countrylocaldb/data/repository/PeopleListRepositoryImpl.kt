package com.example.countrylocaldb.data.repository

import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity_
import com.example.countrylocaldb.data.data_source.remote.api.CountryListApi
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import com.example.countrylocaldb.domain.repository.PeopleListRepository
import io.objectbox.Box
import io.objectbox.query.Query
import retrofit2.Response
import javax.inject.Inject

class PeopleListRepositoryImpl @Inject constructor(
    private val api: CountryListApi,
    private val countryEntityBox: Box<CountryEntity>,
    private val peopleEntityBox: Box<PeopleEntity>,
    private val queryPeople: Query<PeopleEntity>
) : PeopleListRepository {

    override suspend fun getCountriesFromApi(): Response<CountryListDTO> = api.getData()

    override suspend fun putCountriesToBox(entities: List<CountryEntity>) {
        countryEntityBox.put(entities)
        setAllParamsToQueryPeople()
    }

    override fun getQueryPeople(): Query<PeopleEntity> = queryPeople

    private fun setAllParamsToQueryPeople() {
        val allHumanIds = peopleEntityBox.all.map { it.humanId }.toLongArray()
        queryPeople.setParameters(PeopleEntity_.humanId, allHumanIds).publish()
    }
}