package com.example.countrylocaldb.common

enum class ResourceState {
    Loading,
    Success,
    Error
}

//sealed class ResourceState<T>(val data: T? = null, val message: String? = null) {
//    class Loading<T>(data: T? = null) : ResourceState<T>(data)
//    class Success<T>(data: T, message: String? = null) : ResourceState<T>(data, message)
//    class Error<T>(message: String? = null) : ResourceState<T>(message = message)
//}