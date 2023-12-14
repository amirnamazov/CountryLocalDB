package com.example.countrylocaldb.domain.use_case

import com.example.countrylocaldb.domain.repository.CountryListRepository
import javax.inject.Inject

class CountryListUseCase @Inject constructor(private val repository: CountryListRepository) {
    suspend fun getCountryList() = repository.getCountryList()
}