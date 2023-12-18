package com.example.countrylocaldb.data.repository

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CityEntity_
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
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
    private val queryCity: Query<CityEntity>
) : PeopleListRepository {

    override fun isLocalDbEmpty(): Boolean = countryEntityBox.isEmpty

    override suspend fun getCountriesFromApi(): Response<CountryListDTO> = api.getData()

    override suspend fun putCountriesToBox(entities: List<CountryEntity>) =
        countryEntityBox.put(entities)

    override fun getQueryCity(): Query<CityEntity> = queryCity

    override fun clearAllBoxes() = countryEntityBox.removeAll()

    override fun publishAllCities() {
        val cities = countryEntityBox.all.flatMap { it.cityList }
        val idArray = cities.map { it.cityId }.toLongArray()
        queryCity.setParameters(CityEntity_.cityId, idArray).publish()
    }
}