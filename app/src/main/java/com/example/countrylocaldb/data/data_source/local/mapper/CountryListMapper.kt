package com.example.countrylocaldb.data.data_source.local.mapper

import com.example.countrylocaldb.data.data_source.local.entity.CityEntity
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.entity.PeopleEntity
import com.example.countrylocaldb.domain.model.Filter
import com.example.countrylocaldb.domain.model.People

object CountryListMapper {

    @JvmName("country_filter")
    fun List<CountryEntity>.mapToFilterList(): List<Filter> = map { country ->
        Filter(id = country.countryId, name = country.name)
    }

    @JvmName("city_filter")
    fun List<CityEntity>.mapToFilterList(): List<Filter> = map { city ->
        Filter(id = city.cityId, name = city.name)
    }

    fun List<PeopleEntity>.mapToPeopleList(): List<People> = map { people ->
        People(id = people.humanId, name = people.name, surname = people.surname)
    }
}