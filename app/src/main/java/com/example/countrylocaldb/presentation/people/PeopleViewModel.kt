package com.example.countrylocaldb.presentation.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.countrylocaldb.common.NetworkUtils
import com.example.countrylocaldb.common.ResourceState
import com.example.countrylocaldb.data.data_source.local.mapper.CountryListMapper.mapToPeopleList
import com.example.countrylocaldb.domain.model.People
import com.example.countrylocaldb.domain.use_case.PeopleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.objectbox.android.ObjectBoxLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val useCase: PeopleListUseCase,
    private val networkUtils: NetworkUtils
) : ViewModel() {

    val liveDataPeople: LiveData<List<People>> = ObjectBoxLiveData(useCase.getQueryPeople()).map {
        it.mapToPeopleList()
    }

    fun initializePeopleList() {
        if (useCase.isLocalDbEmpty()) setCountriesFromNetworkToLocalDb()
    }

    fun setCountriesFromNetworkToLocalDb() {
        if (networkUtils.isNetworkAvailable())
            viewModelScope.launch {
                useCase.getCountryList().collect { state ->
                    withContext(Dispatchers.Main) {
                        when (state) {
                            is ResourceState.Loading ->
                                EventBus.getDefault().post(true to "")

                            is ResourceState.Error -> {
                                EventBus.getDefault().post(false to state.message!!)
                                useCase.reloadLocalDb()
                            }

                            is ResourceState.Success -> {
                                EventBus.getDefault().post(false to "")
                                useCase.handleSuccess(state.data)
                            }
                        }
                    }
                }
            }
        else {
            EventBus.getDefault().post(false to "No connection.")
            useCase.reloadLocalDb()
        }
    }
}