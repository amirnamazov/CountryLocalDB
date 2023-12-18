package com.example.countrylocaldb.presentation.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.countrylocaldb.common.ResourceState
import com.example.countrylocaldb.data.data_source.local.mapper.CountryListMapper.mapToPeopleList
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
        it.mapToPeopleList()
    }

    private val _liveDataSwipeRefresh = MutableLiveData(false)
    val liveDataSwipeRefresh: LiveData<Boolean> get() = _liveDataSwipeRefresh

    fun initializePeopleList() {
        if (query.find().isEmpty()) setCountriesFromNetworkToLocalDb()
    }

    fun setCountriesFromNetworkToLocalDb() = viewModelScope.launch {
        useCase.getCountryList().collect { state ->
            withContext(Dispatchers.Main) {
                _liveDataSwipeRefresh.value = state is ResourceState.Loading
            }
        }
    }
}