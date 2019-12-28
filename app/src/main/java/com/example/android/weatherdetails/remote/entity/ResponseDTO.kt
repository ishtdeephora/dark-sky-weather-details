package com.example.android.weatherdetails.remote.entity

import com.google.gson.annotations.SerializedName

data class ResponseDTO(

        @field:SerializedName("latitude")
        val latitude: Any? = null,

        @field:SerializedName("longitude")
        val longitude: Any? = null,

        @field:SerializedName("timezone")
        val timezone: String? = null,

        @field:SerializedName("currently")
        val currently: CurrentlyDTO? = null,

        @field:SerializedName("daily")
        val daily: DailyDTO? = null
)


data class DataDTO(

        @field:SerializedName("temperatureMin")
        val temperatureMin: Any? = null,

        @field:SerializedName("temperatureMax")
        val temperatureMax: Any? = null
)

data class DailyDTO(

        @field:SerializedName("data")
        val data: List<DataDTO?>? = null
)

data class CurrentlyDTO(

        @field:SerializedName("temperature")
        val temperature: Any? = null
)