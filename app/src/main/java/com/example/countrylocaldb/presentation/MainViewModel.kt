package com.example.countrylocaldb.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.countrylocaldb.data.data_source.local.mapper.CountryListMapper.mapToCountryList
import com.example.countrylocaldb.domain.model.People
import com.example.countrylocaldb.domain.use_case.CountryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.objectbox.android.ObjectBoxLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: CountryListUseCase) : ViewModel() {

    val liveDataCountries: LiveData<List<People>> by lazy {
        objectBoxLiveData.map {
            it.mapToCountryList().flatMap { country ->
                country.cityList.flatMap { city -> city.peopleList }
            }
        }
    }

    private val objectBoxLiveData = ObjectBoxLiveData(useCase.getQueryCountry())

    fun getCountries() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getCountryList().collect {

        }
    }
}