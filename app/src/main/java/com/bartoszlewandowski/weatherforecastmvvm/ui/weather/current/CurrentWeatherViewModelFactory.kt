package com.bartoszlewandowski.weatherforecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bartoszlewandowski.weatherforecastmvvm.data.provider.UnitProvider
import com.bartoszlewandowski.weatherforecastmvvm.data.repository.ForecastRepository

/**
 * Created by Bartosz Lewandowski on 22.08.2020
 */
class CurrentWeatherViewModelFactory(
	private val forecastRepository: ForecastRepository,
	private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return CurrentWeatherViewModel(forecastRepository, unitProvider) as T
	}
}