package com.example.countrylocaldb.data.data_source.local.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
data class CityEntity(
    @Id var cityId: Long = 0,
    val name: String,
) {
    lateinit var peopleList: ToMany<PeopleEntity>
}