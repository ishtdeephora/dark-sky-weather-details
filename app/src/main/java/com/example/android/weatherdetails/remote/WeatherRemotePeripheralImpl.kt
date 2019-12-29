package com.example.android.weatherdetails.remote

import com.example.android.weatherdetails.business.peripherals.WeatherRemotePeripheral
import com.example.android.weatherdetails.remote.entity.DataEntity

/**
 * [WeatherRemotePeripheralImpl] is the remote implementation for fetching the resource from the remote peripheral of weather details service
 *
 * @param `latitude` - used for latitude of the location
 * @param `longitude` - used for latitude of the location
 * @param `date`
 *
 * This will provide reference to the UseCase creator in the presentation layer
 */

class WeatherRemotePeripheralImpl(private val fetchWeatherDetails: (String, String, String) -> DataEntity) : WeatherRemotePeripheral {
    override fun getWeatherDetails(latitude: String, longitude: String, date: String): DataEntity = fetchWeatherDetails.invoke(latitude, longitude, date)
}