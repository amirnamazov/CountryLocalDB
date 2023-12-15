package com.example.countrylocaldb.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrylocaldb.common.ResourceState
import com.example.countrylocaldb.data.data_source.local.entity.CountryEntity
import com.example.countrylocaldb.domain.use_case.CountryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.objectbox.BoxStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: CountryListUseCase,
    private val boxStore: BoxStore
) : ViewModel() {

    private val _liveDataCountries = MutableLiveData<String>()
    val liveDataCountries get() = _liveDataCountries

    fun getCountries() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getCountryList().collectLatest { res ->
            withContext(Dispatchers.Main) {
                _liveDataCountries.value = when (res) {
                    is ResourceState.Error -> {
                        println(res.message); res.message
                    }

                    is ResourceState.Loading -> {
                        "loooaaddiing"
                    }

                    is ResourceState.Success -> {
                        boxStore.boxFor(CountryEntity::class.java).get(1).toString()
                    }
                }
            }
        }
    }
}