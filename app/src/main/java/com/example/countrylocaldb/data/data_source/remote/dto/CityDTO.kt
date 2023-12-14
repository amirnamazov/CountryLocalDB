package com.example.countrylocaldb.data.data_source.remote.dto

data class CityDTO(
    val cityId: Int? = null,
    val name: String? = null,
    val peopleList: List<PeopleDTO?>? = null
)