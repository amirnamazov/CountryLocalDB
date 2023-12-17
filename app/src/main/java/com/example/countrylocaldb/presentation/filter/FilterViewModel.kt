package com.example.countrylocaldb.presentation.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.countrylocaldb.domain.model.Country
import com.example.countrylocaldb.domain.use_case.FilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.objectbox.android.ObjectBoxLiveData
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(private val useCase: FilterUseCase) : ViewModel() {

    private val query = useCase.getQueryCountry()

    val liveDataCountries: LiveData<List<FilterModel>> = ObjectBoxLiveData(query).map {
        useCase.mapToListCountry(it).mapToFilterList()
    }

    private fun List<Country>.mapToFilterList() =
        map { country -> FilterModel(country.id, country.name) }

    fun getFilters(): List<FilterModel> = liveDataCountries.value ?: emptyList()

    fun checkAllOptions(isChecked: Boolean) =
        getFilters().forEach { filter -> filter.checked.set(isChecked) }
}