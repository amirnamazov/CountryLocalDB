package com.example.countrylocaldb.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.data.data_source.local.mapper.CountryListMapper.mapToCountryList
import com.example.countrylocaldb.domain.model.People
import com.example.countrylocaldb.domain.use_case.CountryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.objectbox.android.ObjectBoxLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: CountryListUseCase) : ViewModel() {

    val liveDataCountries: LiveData<List<People>> get() = objectBoxLiveData.map {
        it.mapToCountryList().flatMap { country ->
            country.cityList.flatMap { city -> city.peopleList }
        }
    }

    private val objectBoxLiveData: ObjectBoxLiveData<CountryEntity> =
        ObjectBoxLiveData(useCase.getQueryCountry())

    fun getCountries() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getCountryList().collectLatest { res ->

            /*withContext(Dispatchers.Main) {
                _liveDataCountries.value = when (res) {
                    ResourceState.Error -> {
                        emptyList()
                    }

                    ResourceState.Loading -> {
                        emptyList()
                    }

                    ResourceState.Success -> {
//                        val list = box.all.flatMap { country ->
//                            country.cityList.flatMap { city -> city.peopleList }
//                        }
                        listOf(
                            People(0, "AMir", "Namazov"),
                            People(1, "7878", "jdjdbds"),
                            People(2, "AM55ir", "Namazov"),
                        )
                    }
                }
            }*/
        }
    }
}