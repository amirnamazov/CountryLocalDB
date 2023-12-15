package com.example.countrylocaldb.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.countrylocaldb.common.ResourceState
import com.example.countrylocaldb.domain.model.People
import com.example.countrylocaldb.domain.use_case.CountryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.objectbox.android.ObjectBoxLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: CountryListUseCase) : ViewModel() {

    private val objectBoxLiveData by lazy { ObjectBoxLiveData(query) }

    private val query get() = useCase.getQueryCountry()

//    private var query by Delegates.observable(useCase.getQueryCountry()) { _, old, new ->
//
//    }

    val liveDataPeople: LiveData<List<People>> by lazy {
        objectBoxLiveData.map { useCase.flatMapToListPeople(it) }
    }

    fun setPeopleList() = with(query.find()) {
        if (useCase.flatMapToListPeople(this).isEmpty())
            getCountriesFromNetwork()
    }

    fun getCountriesFromNetwork() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getCountryList().collect {
            if (it == ResourceState.Success) {
                delay(3000)
                objectBoxLiveData.value
                query.publish()
                query.close()
                query.use {  }
            }
        }
    }
}