package com.example.countrylocaldb.presentation.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.countrylocaldb.domain.model.People
import com.example.countrylocaldb.domain.use_case.PeopleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.objectbox.android.ObjectBoxLiveData
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(private val useCase: PeopleListUseCase) : ViewModel() {

    private val query = useCase.getQueryPeople()

    val liveDataPeople: LiveData<List<People>> = ObjectBoxLiveData(query).map {
        useCase.mapToListPeople(it)
    }
}