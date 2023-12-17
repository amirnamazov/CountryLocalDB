package com.example.countrylocaldb.domain.repository

import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import io.objectbox.query.Query

interface FilterRepository {
    fun getCountryQuery(): Query<CountryEntity>
}