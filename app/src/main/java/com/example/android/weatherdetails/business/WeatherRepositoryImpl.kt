package com.example.android.weatherdetails.business

import com.example.android.weatherdetails.business.peripherals.WeatherRemotePeripheral
import com.example.android.weatherdetails.remote.entity.DataEntity

/**
 * [WeatherRepositoryImpl] is the decision maker for making a call to the remote call whether to make
 * a call to remote layer or storage layer or any other
 */
class WeatherRepositoryImpl(private val remotePeripheral: WeatherRemotePeripheral) : WeatherRepository {
    override fun getWeatherDetails(latitude: String, longitude: String, date: String): DataEntity {
        return remotePeripheral.getWeatherDetails(latitude, longitude, date)
    }
}