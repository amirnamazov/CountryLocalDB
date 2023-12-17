package com.example.countrylocaldb.domain.use_case

import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.mapper.CountryListMapper
import com.example.countrylocaldb.domain.model.Country
import com.example.countrylocaldb.domain.repository.FilterRepository
import io.objectbox.query.Query
import javax.inject.Inject

class FilterUseCase @Inject constructor(private val repository: FilterRepository) {

    fun getQueryCountry(): Query<CountryEntity> = repository.getCountryQuery()

    fun mapToListCountry(entities: List<CountryEntity>): List<Country> = CountryListMapper.run {
        entities.mapToCountryList()
    }
}