package com.example.countrylocaldb.data.data_source.local.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class PeopleEntity(
    @Id var humanId: Long = 0,
    val name: String,
    val surname: String
)