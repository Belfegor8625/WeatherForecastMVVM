package com.bartoszlewandowski.weatherforecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import com.bartoszlewandowski.weatherforecastmvvm.data.repository.ForecastRepository
import com.bartoszlewandowski.weatherforecastmvvm.internal.UnitSystem
import com.bartoszlewandowski.weatherforecastmvvm.internal.lazyDeferred

class CurrentWeatherViewModel(
	private val forecastRepository: ForecastRepository
) : ViewModel() {
	private val unitSystem = UnitSystem.METRIC

	val isMetric: Boolean
		get() = unitSystem == UnitSystem.METRIC

	val weather by lazyDeferred {
		forecastRepository.getCurrentWeather(isMetric)
	}
}