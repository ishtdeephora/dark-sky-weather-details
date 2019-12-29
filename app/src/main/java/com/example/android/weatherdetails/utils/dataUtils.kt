package com.example.android.weatherdetails.utils

import android.content.Context
import android.location.Geocoder
import java.text.SimpleDateFormat
import java.util.*

/**
 * Utility to check whether date on the given string in the UI is prime or not
 * @param `date`
 */

val isPrimeDate: (Int) -> Boolean = { date ->
    (2..date / 2).none { date % it == 0 }
}

/**
 * Utility to provide the current system time for the given format
 * @param `SimpleDateFormat string`
 */

val getCurrentDateInAskedFormat: (String) -> String = {
    SimpleDateFormat(it, Locale.US).format(System.currentTimeMillis())
}

/**
 * Temperature converter from fahrenheit to celcuis scale
 * @param [temperatureFahrenheit]
 */

fun convertFahrenheitToCelsius(temperatureFahrenheit: Double?): Double? =
        if (temperatureFahrenheit != null)
            ((temperatureFahrenheit - 32) * 5) / 9
        else null


/**
 * Utility for fetching the latitude and longitude coordinates for given location
 * @param `context` and `address string`
 *
 * @exception - when a non- valid address is passed to this utility the [Geocoder] utility throws a [NullPointerException]
 * handling for that scenario handled via [kotlin.runCatching]
 *
 * @return Address object containing the latitude and longitude
 */


val geocodeLatLongFetcher: (Context, String) -> Result<Address> =
        { context, address ->
            runCatching {
                Geocoder(context, Locale.US).getFromLocationName(address, 1)[0].run {
                    Address(this.latitude.toString(), this.longitude.toString())
                }
            }
        }

data class Address(val latitude: String, val longitude: String)