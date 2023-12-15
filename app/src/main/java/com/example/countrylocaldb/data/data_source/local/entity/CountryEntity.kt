package com.example.countrylocaldb.data.data_source.local.entity

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
data class CountryEntity(
    @Id(assignable = true) var countryId: Long = 0,
    val name: String = ""
) {
    @Backlink
    lateinit var cityList: ToMany<CityEntity>
}