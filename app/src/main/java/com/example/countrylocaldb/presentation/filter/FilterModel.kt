package com.example.countrylocaldb.presentation.filter

import androidx.databinding.ObservableBoolean

data class FilterModel(
    val id: Long,
    val name: String
) {
    var checked: ObservableBoolean = ObservableBoolean(false)
}