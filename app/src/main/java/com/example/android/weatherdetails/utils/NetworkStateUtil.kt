@file:Suppress("DEPRECATION")

package com.example.android.weatherdetails.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


/**
 * [NetworkStateUtil] object is used for Network connection check
 * @param `context` - the activity context
 * @return `true or false` based on the availability of the netwotk
 */

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
