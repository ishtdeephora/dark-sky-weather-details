package com.example.android.weatherdetails.business

import com.example.android.weatherdetails.business.peripherals.WeatherRemotePeripheral
import com.example.android.weatherdetails.remote.entity.DataEntity

class WeatherRepositoryImpl(private val remotePeripheral: WeatherRemotePeripheral) : WeatherRepository {
    override fun getWeatherDetails(latitude: String, longitude:String, date:String): DataEntity {
        return remotePeripheral.getWeatherDetails(latitude, longitude, date)
    }
}