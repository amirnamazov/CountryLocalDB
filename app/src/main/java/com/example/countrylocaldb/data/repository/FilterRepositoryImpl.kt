package com.example.countrylocaldb.data.repository

import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.domain.repository.FilterRepository
import io.objectbox.query.Query
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor(private val queryCountry: Query<CountryEntity>) :
    FilterRepository {
    override fun getCountryQuery(): Query<CountryEntity> = queryCountry
}