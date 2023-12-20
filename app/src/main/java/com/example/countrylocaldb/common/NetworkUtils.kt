package com.example.countrylocaldb.common

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class NetworkUtils @Inject constructor(private val connectivityManager: ConnectivityManager) {

    fun isNetworkAvailable(): Boolean {
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }
}