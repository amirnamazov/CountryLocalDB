package com.example.countrylocaldb.common

sealed class ResourceState(val message: String? = null) {
    class Loading(message: String? = null) : ResourceState(message)
    class Success(message: String? = null) : ResourceState(message)
    class Error(message: String, val code: Int? = null) : ResourceState(message)
}