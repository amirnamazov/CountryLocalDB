package com.example.countrylocaldb.presentation.people

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
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val useCase: CountryListUseCase) : ViewModel() {

    private val objectBoxLiveData by lazy { ObjectBoxLiveData(query) }

    private val query = useCase.getQuery()

//    private val checkedPeopleList =

//    private val _liveDataPeople = MutableLiveData(useCase.flatMapToListPeople(query.find()))

    val liveDataPeople: LiveData<List<People>> get() = objectBoxLiveData.map {
        useCase.flatMapToListPeople(it)
    }

    fun setPeopleList() = query.find().let {
        if (useCase.flatMapToListPeople(it).isEmpty()) getCountriesFromNetwork()
    }

    fun getCountriesFromNetwork() = viewModelScope.launch {
        useCase.getCountryList().collect {
            withContext(Dispatchers.Main) {
                if (it == ResourceState.Success) {
                    delay(3000)
//                    val property = query.property(CountryEntity_.name)
//                    query.setParameter(CountryEntity_.name, "CountryA")
//                    println("232432321  find  ${property.findStrings().map { it }}")

//                    _liveDataPeople.value = useCase.flatMapToListPeople(query.find()).subList(0, 8)
                }
            }
        }
    }
}