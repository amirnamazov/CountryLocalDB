package com.example.countrylocaldb.data.repository

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CityEntity_
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
    private val boxCountryEntity: Box<CountryEntity>,
    private val queryCity: Query<CityEntity>,
    private val queryPeople: Query<PeopleEntity>
) : PeopleListRepository {

    override fun isLocalDbEmpty(): Boolean = boxCountryEntity.isEmpty

    override suspend fun getCountriesFromApi(): Response<CountryListDTO> = api.getData()

    override suspend fun putCountriesToBox(entities: List<CountryEntity>) =
        boxCountryEntity.put(entities)

    override fun getQueryPeople(): Query<PeopleEntity> = queryPeople

    override fun clearAllBoxes() = boxCountryEntity.removeAll()

    override fun publishAllCitiesAndPeople() {
        val countryIdArray = boxCountryEntity.query().build().findIds()
        queryCity.setParameters(CityEntity_.countryEntityId, countryIdArray).publish()
        queryPeople.setParameters(PeopleEntity_.cityEntityId, queryCity.findIds()).publish()
    }
}