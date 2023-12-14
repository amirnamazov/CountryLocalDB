package com.example.countrylocaldb.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrylocaldb.data.data_source.remote.CountryListApi
import com.example.countrylocaldb.data.data_source.remote.dto.CountryListDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val api: CountryListApi) : ViewModel() {

    private val _liveDataCountries = MutableLiveData<Response<CountryListDTO>>()
    val liveDataCountries get() = _liveDataCountries

    fun getCountries() = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.Main) {
            _liveDataCountries.value = api.getData()
        }
    }
}