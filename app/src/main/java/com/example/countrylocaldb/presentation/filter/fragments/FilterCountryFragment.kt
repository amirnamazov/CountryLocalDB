package com.example.countrylocaldb.presentation.filter.fragments

import com.example.countrylocaldb.presentation.filter.FilterModel

class FilterCountryFragment : FilterFragment() {

    override fun getFilters(): List<FilterModel> = viewModel.countries

    override fun filterOptions() = viewModel.filterCountries()
}