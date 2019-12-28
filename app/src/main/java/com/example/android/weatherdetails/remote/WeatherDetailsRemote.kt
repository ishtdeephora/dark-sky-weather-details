package com.example.android.weatherdetails.remote

import com.example.android.weatherdetails.remote.entity.DataEntity
import com.example.android.weatherdetails.remote.entity.ResponseDTO
import com.example.android.weatherdetails.remote.service.WeatherService
import com.example.android.weatherdetails.remote.service.retrofit
import com.example.android.weatherdetails.remote.service.retrofitCallExecutor
import com.example.android.weatherdetails.utils.convertFahrenheitToCelsius
import retrofit2.Call
import retrofit2.Response

internal class WeatherDetailsRemote(
        private val executor: (Call<ResponseDTO>) -> Response<ResponseDTO> = retrofitCallExecutor()
) {

    fun executor(): (String, String, String) -> DataEntity = { latitude, longitude, date ->
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(latitude = latitude, longitude = longitude, date = date)
        val weatherResponse = executor.invoke(call).body()
        val currentTemp = convertFahrenheitToCelsius(weatherResponse?.currently?.temperature as Double).toString().substring(0, 3) + " ºC"
        val minTemp = convertFahrenheitToCelsius(weatherResponse.daily?.data?.first()?.temperatureMin as Double).toString().substring(0, 3) + " ºC"
        val maxTemp = convertFahrenheitToCelsius(weatherResponse.daily.data.first()?.temperatureMax as Double).toString().substring(0, 3) + " ºC"
        DataEntity(currentTemp, minTemp, maxTemp)
    }

}




