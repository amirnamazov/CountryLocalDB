package com.example.countrylocaldb.presentation.filter.fragments

import com.example.countrylocaldb.presentation.filter.FilterModel

class FilterCityFragment : FilterFragment() {

    override val filters: List<FilterModel> get() = viewModel.cities

    override fun filterOptions() = viewModel.filterCities()
}