package com.example.countrylocaldb.presentation.filter

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.example.countrylocaldb.domain.model.Country
import com.example.countrylocaldb.domain.use_case.FilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(private val useCase: FilterUseCase) : ViewModel(),
    FilterAdapter.ItemClick {

    val filterModels: List<FilterModel> by lazy { useCase.getAllCountries().mapToFilterList() }

    val isAllChecked: ObservableBoolean = ObservableBoolean(true)

    private fun List<Country>.mapToFilterList(): List<FilterModel> =
        map { country -> FilterModel(country.id, country.name) }

    fun onCheckAllClicked() = with(isAllChecked) {
        set(!get())
        filterModels.forEach { filter -> filter.checked.set(get()) }
    }

    override fun onItemClicked(filter: FilterModel) = with(filterModels) {
        find { it == filter }?.checked?.let { it.set(!it.get()) }
        isAllChecked.set(all { it.checked.get() })
    }

    fun publishSelectedCountries() {
        val selectedOptions = filterModels.filterNot { it.checked.get() }
        val idArray = selectedOptions.map { it.id }.toLongArray()
        useCase.publishSelectedCountries(idArray)
    }
}