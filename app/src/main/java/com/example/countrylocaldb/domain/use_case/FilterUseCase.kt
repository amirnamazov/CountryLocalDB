package com.example.countrylocaldb.domain.use_case

import com.example.countrylocaldb.data.data_source.local.mapper.CountryListMapper.mapToCityList
import com.example.countrylocaldb.data.data_source.local.mapper.CountryListMapper.mapToCountryList
import com.example.countrylocaldb.domain.model.City
import com.example.countrylocaldb.domain.model.Country
import com.example.countrylocaldb.domain.repository.FilterRepository
import javax.inject.Inject

class FilterUseCase @Inject constructor(private val repository: FilterRepository) {

    fun getAllCountries(): List<Country> = repository.getAllCountries().mapToCountryList()

    fun getSelectedCities(): List<City> = repository.getSelectedCities().mapToCityList()

    fun filterCountries(idArray: LongArray) = repository.publishSelectedCitiesAndPeople(idArray)

    fun filterCities(idArray: LongArray) = repository.publishSelectedPeople(idArray)

    fun Country.isFiltered(): Boolean = repository.getFilteredCountryIds().contains(id)

    fun City.isFiltered(): Boolean = repository.getFilteredCityIds().contains(id)
}