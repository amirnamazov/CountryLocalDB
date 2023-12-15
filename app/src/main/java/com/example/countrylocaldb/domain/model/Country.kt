package com.example.countrylocaldb.domain.model

data class Country(
    val id: Long,
    val cityList: List<City>,
    val name: String
)