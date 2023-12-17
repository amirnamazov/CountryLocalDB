package com.example.countrylocaldb.presentation.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.countrylocaldb.common.ResourceState
import com.example.countrylocaldb.domain.model.People
import com.example.countrylocaldb.domain.use_case.PeopleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.objectbox.android.ObjectBoxLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val useCase: PeopleListUseCase) : ViewModel() {

    private val query = useCase.getQueryPeople()

    val liveDataPeople: LiveData<List<People>> = ObjectBoxLiveData(query).map {
        useCase.mapToListPeople(it)
    }

    fun initializePeopleList() {
        if (query.find().isEmpty()) getCountriesFromNetwork()
    }

    fun getCountriesFromNetwork() = viewModelScope.launch {
        useCase.getCountryList().collect {
            withContext(Dispatchers.Main) {
                if (it == ResourceState.Success) {
//                    delay(2000)
//                    val longArray2 = longArrayOf(0, 1, 2, 3)
//                    useCase.setParamsToQueryPeople(longArray2)
                }
            }
        }
    }
}