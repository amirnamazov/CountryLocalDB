package com.example.countrylocaldb.domain.repository

import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity

interface FilterRepository {
    fun getAllCountries(): List<CountryEntity>

    fun publishSelectedCountries(idArray: LongArray)
}