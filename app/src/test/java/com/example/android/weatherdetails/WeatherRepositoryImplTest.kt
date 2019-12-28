package com.example.android.weatherdetails

import com.example.android.weatherdetails.business.WeatherRepositoryImpl
import com.example.android.weatherdetails.business.peripherals.WeatherRemotePeripheral
import com.example.android.weatherdetails.remote.WeatherRemotePeripheralImpl
import com.example.android.weatherdetails.remote.entity.DataEntity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class WeatherRepositoryImplTest {

    private lateinit var repoImpl: WeatherRepositoryImpl
    private lateinit var remotePeripheral: WeatherRemotePeripheral
    private lateinit var weatherRemotePeripheralImpl: WeatherRemotePeripheralImpl


    @Before
    fun setup() {
        weatherRemotePeripheralImpl = Mockito.mock(WeatherRemotePeripheralImpl::class.java)
        remotePeripheral = Mockito.mock(WeatherRemotePeripheral::class.java)
        repoImpl = WeatherRepositoryImpl(
                remotePeripheral
        )
    }

    @Test
    fun `positive fetch data entity for cached value of dataEntity`() {
        val cachedDataEntity = Mockito.mock(DataEntity::class.java)
        Mockito.`when`(remotePeripheral.getWeatherDetails(latitude = "0", longitude = "0", date = "28/12/2019")).thenReturn(cachedDataEntity)
        Mockito.`when`(weatherRemotePeripheralImpl.getWeatherDetails(latitude = "0", longitude = "0", date = "28/12/2019")).thenReturn(cachedDataEntity)
        weatherRemotePeripheralImpl.getWeatherDetails(latitude = "0", longitude = "0", date = "28/12/2019").apply {
            Assert.assertEquals(cachedDataEntity, this)
        }.run {
            repoImpl.getWeatherDetails(latitude = "0", longitude = "0", date = "28/12/2019")
        }.apply {
            Assert.assertEquals(cachedDataEntity, this)
        }
    }

    @After
    fun `tear down`() {
        Mockito.validateMockitoUsage()
    }

}