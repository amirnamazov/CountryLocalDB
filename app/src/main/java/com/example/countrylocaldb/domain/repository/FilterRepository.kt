package com.example.countrylocaldb.domain.repository

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity

interface FilterRepository {
    fun getAllCountries(): List<CountryEntity>

    fun publishSelectedCountries(idArray: LongArray)

    fun publishSelectedCities(idArray: LongArray)

    fun getSelectedCities(): List<CityEntity>
}