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

    val filterModels: List<FilterModel> by lazy {
        if (isCountryFilter) useCase.getAllCountries().map { FilterModel(it.id, it.name) }
        else useCase.getSelectedCities().map { FilterModel(it.id, it.name) }
    }

    val isAllChecked: ObservableBoolean = ObservableBoolean(true)

    fun onCheckAllClicked() = with(isAllChecked) {
        set(!get())
        filterModels.forEach { filter -> filter.checked.set(get()) }
    }

    override fun onItemClicked(filter: FilterModel) = with(filterModels) {
        find { it == filter }?.checked?.let { it.set(!it.get()) }
        isAllChecked.set(all { it.checked.get() })
    }

    fun publishSelectedOptions() {
        if (isCountryFilter) useCase.filterCountries(selectedIds())
        else useCase.filterCities(selectedIds())
    }

    private fun selectedIds() = filterModels.filter { it.checked.get() }
        .map { it.id }.toLongArray()
}