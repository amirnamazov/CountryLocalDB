package com.example.countrylocaldb.data.repository

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CityEntity_
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity_
import com.example.countrylocaldb.domain.repository.FilterRepository
import io.objectbox.Box
import io.objectbox.query.Query
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor(
    private val countryEntityBox: Box<CountryEntity>,
//    private val cityEntityBox: Box<CityEntity>,
    private val queryCountry: Query<CountryEntity>,
    private val queryCity: Query<CityEntity>,
//    private val queryPeople: Query<PeopleEntity>
) : FilterRepository {

    override fun getAllCountries(): List<CountryEntity> = countryEntityBox.all

    override fun publishSelectedCountries(idArray: LongArray) {
        queryCountry.setParameters(CountryEntity_.countryId, idArray).publish()


        val cities = queryCountry.find().flatMap { it.cityList }
        val cityIdArray = cities.map { it.cityId }.toLongArray()
        queryCity.setParameters(CityEntity_.cityId, cityIdArray)
//
//        val peopleList = cities.flatMap { it.peopleList }
//        val peopleIdArray = peopleList.map { it.humanId }.toLongArray()
//        queryPeople.setParameters(PeopleEntity_.humanId, peopleIdArray).publish()
    }
}