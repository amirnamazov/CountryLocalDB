package com.example.countrylocaldb.data.data_source.local.mapper

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.domain.model.City
import com.example.countrylocaldb.domain.model.Country
import com.example.countrylocaldb.domain.model.People

object CountryListMapper {

    fun List<CountryEntity>.mapToCountryList(): List<Country> = map { country ->
        Country(
            id = country.countryId,
            name = country.name,
            cityList = country.cityList.mapToCityList()
        )
    }

    private fun List<CityEntity>.mapToCityList(): List<City> = map { city ->
        City(id = city.cityId, name = city.name, peopleList = city.peopleList.mapToPeopleList())
    }

    private fun List<PeopleEntity>.mapToPeopleList(): List<People> = map { people ->
        People(id = people.humanId, name = people.name, surname = people.surname)
    }
}