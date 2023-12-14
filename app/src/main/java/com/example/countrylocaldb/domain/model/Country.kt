package com.example.countrylocaldb.domain.model

data class Country(
    val cityList: List<City>,
    val name: String
)