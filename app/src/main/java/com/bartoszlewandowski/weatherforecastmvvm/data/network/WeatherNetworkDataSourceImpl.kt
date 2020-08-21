package com.bartoszlewandowski.weatherforecastmvvm.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bartoszlewandowski.weatherforecastmvvm.data.network.response.CurrentWeatherResponse
import com.bartoszlewandowski.weatherforecastmvvm.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
	private val weatherstackApiService: WeatherstackApiService) : WeatherNetworkDataSource {

	private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
	override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
		get() = _downloadedCurrentWeather

	override suspend fun fetchCurrentWeather(location: String, units: String) {
		try {
			val fetchedCurrentWeather = weatherstackApiService
				.getCurrentWeather(location = location, units = units)
			_downloadedCurrentWeather.postValue(fetchedCurrentWeather)
		} catch (e: NoConnectivityException) {
			Log.e("Connectivity", "No internet connection", e)
		}
	}
}