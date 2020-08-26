package com.bartoszlewandowski.weatherforecastmvvm.data.provider

import com.bartoszlewandowski.weatherforecastmvvm.data.db.entity.WeatherLocation

/**
 * Created by Bartosz Lewandowski on 25.08.2020
 */
interface LocationProvider {
	suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
	suspend fun getPreferredLocationString(): String
}