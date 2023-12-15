package com.example.countrylocaldb.domain.model

data class City(
    val id: Long,
    val name: String,
    val peopleList: List<People>
)