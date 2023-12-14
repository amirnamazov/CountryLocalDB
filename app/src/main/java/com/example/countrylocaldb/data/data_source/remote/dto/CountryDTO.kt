package com.example.countrylocaldb.data.data_source.remote.dto

data class CountryDTO(
    val cityList: List<CityDTO?>? = null,
    val countryId: Int? = null,
    val name: String? = null
)