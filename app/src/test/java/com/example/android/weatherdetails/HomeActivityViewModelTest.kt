package com.example.android.weatherdetails

import com.example.android.weatherdetails.business.WeatherRepository
import com.example.android.weatherdetails.business.WeatherRepositoryImpl
import com.example.android.weatherdetails.presentation.HomeActivityViewModel
import com.example.android.weatherdetails.remote.entity.DataEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class HomeActivityViewModelTest {

    private lateinit var viewModel: HomeActivityViewModel

    private lateinit var weatherRepo: WeatherRepository

    private lateinit var dataEntity: DataEntity

    private lateinit var modelEntity: HomeActivityViewModel.WeatherDetailsData

    @Before
    fun setup() {
        viewModel = Mockito.mock(HomeActivityViewModel::class.java)
        weatherRepo = Mockito.mock(WeatherRepositoryImpl::class.java)
        dataEntity = Mockito.mock(DataEntity::class.java)
        modelEntity = Mockito.mock(HomeActivityViewModel.WeatherDetailsData::class.java)
    }

    @Test
    fun `verify invocation of the data entity on initialization`() {
        val latitude = "0"
        val longitude = "0"
        val utcDate = "12345678"

        Mockito.`when`(dataEntity.temp).thenReturn("0 ºC")
        Mockito.`when`(dataEntity.minTemp).thenReturn("0 ºC")
        Mockito.`when`(dataEntity.minTemp).thenReturn("0 ºC")

        Mockito.`when`(weatherRepo.getWeatherDetails(latitude, longitude, utcDate)).thenReturn(dataEntity)
        modelEntity.apply {
            Mockito.`when`(modelEntity.temp).thenReturn("0 ºC")
            Mockito.`when`(modelEntity.minTemp).thenReturn("0 ºC")
            Mockito.`when`(modelEntity.maxTemp).thenReturn("0 ºC")
        }

        viewModel.initialize(weatherRepo, latitude, longitude, utcDate)
    }


    @After
    fun tearDown() {
        Mockito.validateMockitoUsage()
    }
}