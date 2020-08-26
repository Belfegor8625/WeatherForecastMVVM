package com.bartoszlewandowski.weatherforecastmvvm.ui.weather.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bartoszlewandowski.weatherforecastmvvm.R
import com.bartoszlewandowski.weatherforecastmvvm.ui.base.ScopedFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance

class CurrentWeatherFragment : ScopedFragment(), DIAware {

	override val di by closestDI()
	private val viewModelFactory: CurrentWeatherViewModelFactory by instance()
	private lateinit var viewModel: CurrentWeatherViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.current_weather_fragment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProvider(this, viewModelFactory)
			.get(CurrentWeatherViewModel::class.java)

		bindUI()
	}

	private fun bindUI() = launch {
		val currentWeather = viewModel.weather.await()
		val weatherLocation = viewModel.weatherLocation.await()

		currentWeather.observe(viewLifecycleOwner, Observer {
			if (it == null) return@Observer

			group_loading.visibility = View.GONE
			updateLocation("Los Angeles")
			updateDateToToday()
			updateTemperature(it.temperature, it.feelslike)
			updateCondition(it.weatherDescriptions)
			updatePrecipitation(it.precip)
			updateWind(it.windDir, it.windSpeed)
			updateVisibility(it.visibility)

			Glide.with(this@CurrentWeatherFragment)
				.load(it.weatherIcons[0])
				.into(imageView_condition_icon)
		})

		weatherLocation.observe(viewLifecycleOwner, Observer { location ->
			if (location == null) return@Observer
			updateLocation(location.name)
		})
	}

	private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String): String {
		return if (viewModel.isMetric) metric else imperial
	}

	private fun updateLocation(location: String) {
		(activity as AppCompatActivity).supportActionBar?.title = location
	}

	private fun updateDateToToday() {
		(activity as AppCompatActivity).supportActionBar?.subtitle = "Today"
	}

	private fun updateTemperature(temperature: Int, feelsLike: Int) {
		val unitAbbreviation = chooseLocalizedUnitAbbreviation("°C", "°F")
		textView_temperature.text = getString(R.string.temperature, temperature, unitAbbreviation)
		textView_feels_like_temperature.text =
			getString(R.string.feels_like, feelsLike, unitAbbreviation)
	}

	private fun updateCondition(condition: List<String>) {
		textView_condition.text = condition.joinToString()
	}

	private fun updatePrecipitation(precipitationVolume: Double) {
		val unitAbbreviation = chooseLocalizedUnitAbbreviation("mm", "in")
		textView_precipitation.text =
			getString(R.string.precipitation, precipitationVolume, unitAbbreviation)
	}

	private fun updateWind(windDirection: String, windSpeed: Int) {
		val unitAbbreviation = chooseLocalizedUnitAbbreviation("kpm", "mph")
		textView_wind.text = getString(R.string.wind, windDirection, windSpeed, unitAbbreviation)
	}

	private fun updateVisibility(visibilityDistance: Int) {
		val unitAbbreviation = chooseLocalizedUnitAbbreviation("km", "mi")
		textView_visibility.text =
			getString(R.string.visibility, visibilityDistance, unitAbbreviation)
	}
}