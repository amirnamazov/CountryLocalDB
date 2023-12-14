package com.example.countrylocaldb.domain.repository

import com.example.countrylocaldb.common.ResourceState
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import kotlinx.coroutines.flow.Flow

interface CountryListRepository {
    suspend fun getCountryList(): Flow<ResourceState<List<CountryEntity>>>
}