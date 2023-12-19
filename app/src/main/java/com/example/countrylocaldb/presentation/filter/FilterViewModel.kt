package com.example.countrylocaldb.presentation.filter

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.example.countrylocaldb.domain.use_case.FilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(private val useCase: FilterUseCase) : ViewModel(),
    FilterAdapter.ItemClick {

    var isCountryFilter = true

    val filters: List<FilterModel> by lazy {
        useCase.run {
            if (isCountryFilter) getAllCountries().map {
                FilterModel(it.id, it.name, ObservableBoolean(it.isFiltered()))
            }
            else getSelectedCities().map {
                FilterModel(it.id, it.name, ObservableBoolean(it.isFiltered()))
            }
        }
    }

    val isAllChecked by lazy { ObservableBoolean(filters.all { it.checked.get() }) }

    fun onCheckAllClicked() = with(isAllChecked) {
        set(!get())
        filters.forEach { filter -> filter.checked.set(get()) }
    }

    override fun onItemClicked(filter: FilterModel) = with(filters) {
        find { it == filter }?.checked?.let { it.set(!it.get()) }
        isAllChecked.set(all { it.checked.get() })
    }

    fun filterOptions() {
        if (isCountryFilter) useCase.filterCountries(selectedIds())
        else useCase.filterCities(selectedIds())
    }

    private fun selectedIds() = filters.filter { it.checked.get() }.map { it.id }.toLongArray()
}