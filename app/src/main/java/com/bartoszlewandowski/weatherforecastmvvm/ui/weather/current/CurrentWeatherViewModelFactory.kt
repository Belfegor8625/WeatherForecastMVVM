package com.bartoszlewandowski.weatherforecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bartoszlewandowski.weatherforecastmvvm.data.repository.ForecastRepository

/**
 * Created by Bartosz Lewandowski on 22.08.2020
 */
class CurrentWeatherViewModelFactory(
	private val forecastRepository: ForecastRepository
) : ViewModelProvider.NewInstanceFactory() {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return CurrentWeatherViewModel(forecastRepository) as T
	}
}