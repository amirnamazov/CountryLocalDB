package com.example.countrylocaldb.data.data_source.remote.mapper

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.data.data_source.remote.dto.CityDTO
import com.example.countrylocaldb.data.data_source.remote.dto.CountryDTO
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO

object CountryListMapper {
    fun CountryListDTO.mapToCountryEntities(): List<CountryEntity> = countryList?.map {
        val countryEntity = CountryEntity(name = it?.name ?: "").apply {
            it?.mapToCityEntities()?.let { list -> cityList.addAll(list) }
        }
        countryEntity.apply { countryId = it?.countryId?.toLong() ?: 0 }
    } ?: emptyList()

    private fun CountryDTO.mapToCityEntities(): List<CityEntity> = cityList?.map {
        val cityEntity = CityEntity(name = it?.name ?: "").apply {
            it?.mapToPeopleEntities()?.let { list -> peopleList.addAll(list) }
        }
        cityEntity.apply { cityId = it?.cityId?.toLong() ?: 0 }
    } ?: emptyList()

    private fun CityDTO.mapToPeopleEntities(): List<PeopleEntity> = peopleList?.map {
        val peopleEntity = PeopleEntity(
            name = it?.name ?: "",
            surname = it?.surname ?: "",
        )
        peopleEntity.apply { humanId = it?.humanId?.toLong() ?: 0 }
    } ?: emptyList()
}