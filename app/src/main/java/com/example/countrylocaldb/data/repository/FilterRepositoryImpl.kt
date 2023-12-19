package com.example.countrylocaldb.data.repository

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CityEntity_
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity_
import com.example.countrylocaldb.domain.repository.FilterRepository
import io.objectbox.Box
import io.objectbox.query.Query
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor(
    private val boxCountryEntity: Box<CountryEntity>,
    private val queryCity: Query<CityEntity>,
    private val queryPeople: Query<PeopleEntity>
) : FilterRepository {

    override fun getAllCountries(): List<CountryEntity> = boxCountryEntity.all

    override fun publishSelectedCitiesAndPeople(idArray: LongArray) {
        queryCity.setParameters(CityEntity_.countryEntityId, idArray).publish()
        publishSelectedPeople(queryCity.findIds())
    }

    override fun publishSelectedPeople(idArray: LongArray) =
        queryPeople.setParameters(PeopleEntity_.cityEntityId, idArray).publish()

    override fun getSelectedCities(): List<CityEntity> = queryCity.find()
}