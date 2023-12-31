package com.example.countrylocaldb.presentation.filter

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.example.countrylocaldb.domain.use_case.FilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(private val useCase: FilterUseCase) : ViewModel() {

    val countries = useCase.getAllCountries().map {
        FilterModel(it.id, it.name, ObservableBoolean(useCase.isCountryFiltered(it)))
    }

    val cities = useCase.getSelectedCities().map {
        FilterModel(it.id, it.name, ObservableBoolean(useCase.isCityFiltered(it)))
    }

    fun filterCountries() = useCase.filterCountries(countries.selectedIds())

    fun filterCities() = useCase.filterCities(cities.selectedIds())

    private fun List<FilterModel>.selectedIds() =
        filter { it.checked.get() }.map { it.id }.toLongArray()

    fun List<FilterModel>.isAllChecked() = all { it.checked.get() }

    fun ObservableBoolean.checkAll(filters: List<FilterModel>) {
        set(!get())
        filters.forEach { it.checked.set(get()) }
    }

    fun FilterModel.check(allChecked: ObservableBoolean, filters: List<FilterModel>) {
        checked.set(!checked.get())
        allChecked.set(filters.isAllChecked())
    }
}