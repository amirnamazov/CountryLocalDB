package com.example.countrylocaldb.data.data_source.local.entity

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne

@Entity
data class CityEntity(
    @Id(assignable = true) var cityId: Long = 0,
    val name: String = "",
) {
    lateinit var countryEntity: ToOne<CountryEntity>

    @Backlink
    lateinit var peopleList: ToMany<PeopleEntity>
}