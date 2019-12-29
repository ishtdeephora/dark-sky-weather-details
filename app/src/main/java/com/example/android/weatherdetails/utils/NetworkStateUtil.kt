package com.example.android.weatherdetails.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


object NetworkStateUtil : (Context) -> Boolean {
    override fun invoke(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            @Suppress("DEPRECATION") val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            @Suppress("DEPRECATION")
            networkInfo?.isConnected ?: false
        } else false

    }
}
