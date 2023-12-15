package com.example.countrylocaldb.data.data_source.local.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class PeopleEntity(
    @Id(assignable = true) var humanId: Long = 0,
    val name: String = "",
    val surname: String = ""
) {
    lateinit var cityEntity: ToOne<CityEntity>
}