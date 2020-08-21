package com.bartoszlewandowski.weatherforecastmvvm.data.network

import androidx.lifecycle.LiveData
import com.bartoszlewandowski.weatherforecastmvvm.data.network.response.CurrentWeatherResponse

/**
 * Created by Bartosz Lewandowski on 21.08.2020
 */
interface WeatherNetworkDataSource {
	val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

	suspend fun fetchCurrentWeather(
		location: String,
		units: String
	)
}