package com.example.countrylocaldb.data.data_source.remote.mapper

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.data.data_source.remote.dto.CityDTO
import com.example.countrylocaldb.data.data_source.remote.dto.CountryDTO
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import io.objectbox.BoxStore

object CountryListMapper {
    fun CountryListDTO.mapToCountryEntities(boxStore: BoxStore): List<CountryEntity> =
        countryList?.map {
            val countryEntity = CountryEntity(name = it?.name ?: "").apply {
                it?.mapToCityEntities(boxStore)?.let { list ->
                    cityList.addAll(list)
                }
            }
            countryEntity.countryId = it?.countryId?.toLong() ?: 0
            boxStore.boxFor(CountryEntity::class.java).put(countryEntity)
            countryEntity
        } ?: emptyList()

    private fun CountryDTO.mapToCityEntities(boxStore: BoxStore): List<CityEntity> = cityList?.map {
        val cityEntity = CityEntity(name = it?.name ?: "").apply {
            it?.mapToPeopleEntities()?.let { list ->
                peopleList.addAll(list)
            }
        }
        cityEntity.cityId = it?.cityId?.toLong() ?: 0
        boxStore.boxFor(CityEntity::class.java).put(cityEntity)
        cityEntity
    } ?: emptyList()

    private fun CityDTO.mapToPeopleEntities(): List<PeopleEntity> = peopleList?.map {
        val peopleEntity = PeopleEntity(
            name = it?.name ?: "",
            surname = it?.surname ?: "",
        )
        peopleEntity.humanId = it?.humanId?.toLong() ?: 0
        peopleEntity
    } ?: emptyList()
}