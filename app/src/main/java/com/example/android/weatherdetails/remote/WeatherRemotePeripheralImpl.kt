package com.example.android.weatherdetails.remote

import com.example.android.weatherdetails.business.peripherals.WeatherRemotePeripheral
import com.example.android.weatherdetails.remote.entity.DataEntity

class WeatherRemotePeripheralImpl(private val fetchWeatherDetails: (String, String, String) -> DataEntity) : WeatherRemotePeripheral {
    override fun getWeatherDetails(latitude: String, longitude: String, date: String): DataEntity = fetchWeatherDetails.invoke(latitude, longitude, date)
}