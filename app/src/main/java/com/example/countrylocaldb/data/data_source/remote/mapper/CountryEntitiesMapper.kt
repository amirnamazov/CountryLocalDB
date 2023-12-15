package com.example.countrylocaldb.data.data_source.remote.mapper

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.data.data_source.remote.dto.CityDTO
import com.example.countrylocaldb.data.data_source.remote.dto.CountryDTO
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO

object CountryEntitiesMapper {

    fun CountryListDTO.mapToCountryEntities(): List<CountryEntity> =
        countryList?.filterNotNull()?.filterNot { it.name.isNullOrEmpty() }?.map { country ->
            val countryEntity = CountryEntity(name = country.name!!).apply {
                country.mapToCityEntities().let { list -> cityList.addAll(list) }
            }
            countryEntity.apply { countryId = country.countryId?.toLong() ?: 0 }
        } ?: emptyList()

    private fun CountryDTO.mapToCityEntities(): List<CityEntity> =
        cityList?.filterNotNull()?.filterNot { it.name.isNullOrEmpty() }?.map { city ->
            val cityEntity = CityEntity(name = city.name!!).apply {
                city.mapToPeopleEntities().let { list -> peopleList.addAll(list) }
            }
            cityEntity.apply { cityId = city.cityId?.toLong() ?: 0 }
        } ?: emptyList()

    private fun CityDTO.mapToPeopleEntities(): List<PeopleEntity> = peopleList?.filterNotNull()
        ?.filterNot { it.name.isNullOrEmpty() && it.surname.isNullOrEmpty() }?.map { people ->
            val peopleEntity = PeopleEntity(
                name = people.name ?: "",
                surname = people.surname ?: "",
            )
            peopleEntity.apply { humanId = people.humanId?.toLong() ?: 0 }
        } ?: emptyList()
}