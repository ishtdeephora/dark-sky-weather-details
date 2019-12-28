package com.example.android.weatherdetails.remote.service

import com.example.android.weatherdetails.remote.entity.ResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Weather service creator for calling the dark sky api
 * For more information, check out the API docs
 *
 * @param latitude - getting from Geocoder API of the city selected
 * @param longitude -  getting from Geocoder API of the city selected
 * @param date - For current, past or fetching the future date based weather information
 *
 * This call will exclude the hourly DTO flag and offset field to call only the selected day for this app
 */

interface WeatherService {
    @GET("{latitude},{longitude},{date}?exclude=hourly,flags,offset")
    fun getCurrentWeatherData(@Path("latitude") latitude: String,
                              @Path("longitude") longitude: String,
                              @Path("date") date: String): Call<ResponseDTO>
}