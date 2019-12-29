package com.example.android.weatherdetails.utils

import android.content.Context
import android.location.Geocoder
import java.text.SimpleDateFormat
import java.util.*

val isPrimeDate: (Int) -> Boolean = { date ->
    (2..date / 2).none { date % it == 0 }
}

val getCurrentDateInAskedFormat: (String) -> String = {
    SimpleDateFormat(it, Locale.US).format(System.currentTimeMillis())
}

fun convertFahrenheitToCelsius(temperatureFahrenheit: Double?): Double? =
        if (temperatureFahrenheit != null)
            ((temperatureFahrenheit - 32) * 5) / 9
        else null

val geocoderLatLongFetcher: (Context, String) -> Result<Address> =
        { context, address ->
            runCatching {
                Geocoder(context, Locale.US).getFromLocationName(address, 1).get(0).run {
                    Address(this.latitude.toString(), this.longitude.toString())
                }
            }
        }

data class Address(val latitude: String, val longitude: String)