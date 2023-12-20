package com.example.countrylocaldb.presentation.filter.fragments

import com.example.countrylocaldb.presentation.filter.FilterModel

class FilterCountryFragment : FilterFragment() {

    override val filters: List<FilterModel> get() = viewModel.countries

    override fun filterOptions() = viewModel.filterCountries()
}