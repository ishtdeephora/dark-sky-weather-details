package com.example.android.weatherdetails.remote.usecase

import com.example.android.weatherdetails.business.peripherals.WeatherRemotePeripheral
import com.example.android.weatherdetails.remote.WeatherDetailsRemote
import com.example.android.weatherdetails.remote.WeatherRemotePeripheralImpl

fun weatherRemotePeripheral(): WeatherRemotePeripheral {
    return WeatherRemotePeripheralImpl(WeatherDetailsRemote().executor())
}