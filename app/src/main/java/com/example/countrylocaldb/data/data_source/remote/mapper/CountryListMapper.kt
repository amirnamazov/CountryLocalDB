package com.example.countrylocaldb.data.data_source.remote.mapper

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.data.data_source.remote.dto.CityDTO
import com.example.countrylocaldb.data.data_source.remote.dto.CountryDTO
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO

object CountryListMapper {

    fun maptoCountryList(countryListDTO: CountryListDTO): List<CountryEntity> =
        countryListDTO.countryList?.map {
            CountryEntity(
                countryId = it?.countryId ?: 0,
                name = it?.name ?: ""
            ).apply {
                it?.mapToCityList()?.let { list -> cityList.addAll(list) }
            }
        } ?: emptyList()

    private fun CountryDTO.mapToCityList(): List<CityEntity> = cityList?.map {
        CityEntity(
            cityId = it?.cityId ?: 0,
            name = it?.name ?: "",
        ).apply {
            it?.mapToPeopleList()?.let { list -> peopleList.addAll(list) }
        }
    } ?: emptyList()

    private fun CityDTO.mapToPeopleList(): List<PeopleEntity> = peopleList?.map {
        PeopleEntity(
            humanId = it?.humanId ?: 0,
            name = it?.name ?: "",
            surname = it?.surname ?: "",
        )
    } ?: emptyList()
}