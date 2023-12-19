package com.example.countrylocaldb.domain.use_case

import com.example.countrylocaldb.data.data_source.local.mapper.CountryListMapper.mapToFilterList
import com.example.countrylocaldb.domain.model.Filter
import com.example.countrylocaldb.domain.repository.FilterRepository
import javax.inject.Inject

class FilterUseCase @Inject constructor(private val repository: FilterRepository) {

    fun getAllCountries(): List<Filter> = repository.getAllCountries().mapToFilterList()

    fun getSelectedCities(): List<Filter> = repository.getSelectedCities().mapToFilterList()

    fun filterCountries(idArray: LongArray) = repository.publishSelectedCitiesAndPeople(idArray)

    fun filterCities(idArray: LongArray) = repository.publishSelectedPeople(idArray)

    private val filteredCountryIds by lazy { repository.getFilteredCountryIds() }

    fun isCountryFiltered(filter: Filter): Boolean = filteredCountryIds.contains(filter.id)

    private val filteredCityIds by lazy { repository.getFilteredCityIds() }

    fun isCityFiltered(filter: Filter): Boolean = filteredCityIds.contains(filter.id)
}