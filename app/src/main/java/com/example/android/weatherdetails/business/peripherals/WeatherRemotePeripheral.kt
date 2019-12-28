package com.example.android.weatherdetails.business.peripherals

import com.example.android.weatherdetails.remote.entity.DataEntity

interface WeatherRemotePeripheral {

    fun getWeatherDetails(latitude: String, longitude: String, date: String): DataEntity
}