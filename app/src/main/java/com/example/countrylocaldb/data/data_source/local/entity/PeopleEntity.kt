package com.example.countrylocaldb.data.data_source.local.entity

import io.objectbox.annotation.Id

data class PeopleEntity(
    @Id val humanId: Int = 0,
    val name: String,
    val surname: String
)