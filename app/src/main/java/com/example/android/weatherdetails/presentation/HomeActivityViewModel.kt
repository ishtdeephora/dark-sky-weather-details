package com.example.android.weatherdetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.weatherdetails.business.WeatherRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * RxJava implementation for HomeActivity
 */
class HomeActivityViewModel : ViewModel() {

    internal val weatherDetailsLiveData = MutableLiveData<WeatherDetailsData>()

    internal val errorLiveData: LiveData<String> by lazy { MutableLiveData<String>() }

    private var disposable: Disposable? = null

    fun initialize(weatherRepo: WeatherRepository, latitude: String, longitude: String, date: String) {
        disposable = Single.just(weatherRepo).map {
            it.getWeatherDetails(latitude, longitude, date)
        }.map {
            WeatherDetailsData(it.temp, it.minTemp, it.maxTemp)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    weatherDetailsLiveData.value = it
                }, {
            (errorLiveData as MutableLiveData).value = it.localizedMessage
        }
        )
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    internal data class WeatherDetailsData(
            val temp: String,
            val minTemp: String,
            val maxTemp: String
    )
}
