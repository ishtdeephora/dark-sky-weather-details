package com.example.android.weatherdetails.business

import com.example.android.weatherdetails.remote.entity.DataEntity


interface WeatherRepository {
    fun getWeatherDetails(latitude: String, longitude: String, date: String): DataEntity
}