package com.example.countrylocaldb.domain.repository

import com.example.countrylocaldb.common.ResourceState
import com.example.countrylocaldb.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface CountryListRepository {
    suspend fun getCountriesRemote(): Flow<ResourceState>

    suspend fun getCountriesLocal(): List<Country>
}