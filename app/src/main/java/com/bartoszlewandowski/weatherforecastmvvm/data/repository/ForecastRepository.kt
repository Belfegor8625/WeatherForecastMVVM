package com.bartoszlewandowski.weatherforecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.bartoszlewandowski.weatherforecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

/**
 * Created by Bartosz Lewandowski on 22.08.2020
 */

interface ForecastRepository {
	suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}