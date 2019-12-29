package com.example.android.weatherdetails.business.peripherals

import com.example.android.weatherdetails.remote.entity.DataEntity

/**
 * repository client implementation for the storage layer
 */

@Suppress("unused")
interface WeatherPersistentPeripheral {
    fun getWeatherDetails(latitude: String, longitude: String, date: String): DataEntity
}