package com.example.android.weatherdetails.remote.service

import com.example.android.weatherdetails.remote.entity.ResponseDTO
import com.example.android.weatherdetails.remote.remoteexceptions.RemotePeripheralUnauthorizedException
import com.example.android.weatherdetails.remote.remoteexceptions.RequestedDataNotFoundException
import retrofit2.Call
import retrofit2.Response

/**
 * Response checker for [WeatherService] handles different exceptions based on the http codes
 */
fun retrofitCallExecutor(): (Call<ResponseDTO>) -> Response<ResponseDTO> = {
    it.execute().run {
        when {
            isSuccessful -> this
            code() == 401 -> {
                it.execute().apply {
                    if (!isSuccessful) throw RemotePeripheralUnauthorizedException()
                }
            }
            code() == 404 -> throw  RequestedDataNotFoundException("Data not available")
            else -> this
        }
    }
}