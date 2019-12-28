package com.example.android.weatherdetails.presentation

import com.example.android.weatherdetails.business.WeatherRepository
import com.example.android.weatherdetails.business.WeatherRepositoryImpl
import com.example.android.weatherdetails.remote.usecase.weatherRemotePeripheral

internal val WEATHER_REPO: () -> WeatherRepository = {
    WeatherRepositoryImpl(remotePeripheral = weatherRemotePeripheral())
}