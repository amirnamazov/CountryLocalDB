package com.example.countrylocaldb.domain.repository

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity

interface FilterRepository {
    fun getAllCountries(): List<CountryEntity>

    fun getSelectedCities(): List<CityEntity>

    fun publishSelectedCitiesAndPeople(idArray: LongArray)

    fun publishSelectedPeople(idArray: LongArray)

    fun getFilteredCountryIds(): Set<Long>

    fun getFilteredCityIds(): Set<Long>
}