package com.example.countrylocaldb.presentation.people

sealed interface PeopleResponseEvent {
    data object Loading: PeopleResponseEvent
    data class Error(val message: String): PeopleResponseEvent
    data object Success: PeopleResponseEvent
}