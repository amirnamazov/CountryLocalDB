package com.example.countrylocaldb.domain.use_case

import com.example.countrylocaldb.data.data_source.local.mapper.CountryListMapper.mapToCountryList
import com.example.countrylocaldb.domain.model.Country
import com.example.countrylocaldb.domain.repository.FilterRepository
import javax.inject.Inject

class FilterUseCase @Inject constructor(private val repository: FilterRepository) {

    fun getAllCountries(): List<Country> = repository.getAllCountries().mapToCountryList()

    fun publishSelectedCountries(idArray: LongArray) =
        repository.publishSelectedCountries(idArray)
}