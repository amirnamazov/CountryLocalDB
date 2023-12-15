package com.example.countrylocaldb.domain.repository

import com.example.countrylocaldb.common.ResourceState
import kotlinx.coroutines.flow.Flow

interface CountryListRepository {
    suspend fun getCountryList(): Flow<ResourceState<String>>
}