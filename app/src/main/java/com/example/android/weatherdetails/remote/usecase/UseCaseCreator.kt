package com.example.android.weatherdetails.remote.usecase

import com.example.android.weatherdetails.business.peripherals.WeatherRemotePeripheral
import com.example.android.weatherdetails.remote.WeatherDetailsRemote
import com.example.android.weatherdetails.remote.WeatherRemotePeripheralImpl

/**
 * Use case creator for the remote implementation of [WeatherDetailsRemote]
 * only this can call the actual [WeatherDetailsRemote.executor] function to fetch the api response
 */

fun weatherRemotePeripheral(): WeatherRemotePeripheral {
    return WeatherRemotePeripheralImpl(WeatherDetailsRemote().executor())
}