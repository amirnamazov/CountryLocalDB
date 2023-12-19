package com.example.countrylocaldb.common

sealed class ResourceState<T>(val message: String? = null) {
    class Loading<T>(message: String? = null) : ResourceState<T>(message)
    class Success<T>(val data: T, message: String? = null) : ResourceState<T>(message)
    class Error<T>(message: String, val code: Int? = null) : ResourceState<T>(message)
}