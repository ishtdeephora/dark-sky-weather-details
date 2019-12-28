package com.example.android.weatherdetails

import com.example.android.weatherdetails.utils.convertFahrenheitToCelsius
import com.example.android.weatherdetails.utils.isPrimeDate
import org.junit.Assert
import org.junit.Test

class UtilsTest {

    @Test
    fun `test value conversion from f scale to celcius scale`() {
        Assert.assertEquals(18.333333333333332, convertFahrenheitToCelsius(65.0))
    }

    @Test
    fun `prime date test`() {
        Assert.assertEquals(true, isPrimeDate(1))
    }


    @Test
    fun `prime date test for value 23`() {
        Assert.assertEquals(true, isPrimeDate(23))
    }

    @Test
    fun `prime date test for value 27`() {
        Assert.assertEquals(false, isPrimeDate(27))
    }
}