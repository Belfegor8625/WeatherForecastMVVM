package com.bartoszlewandowski.weatherforecastmvvm.ui.weather.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bartoszlewandowski.weatherforecastmvvm.R

class CurrentWeatherFragment : Fragment() {

	companion object {
		fun newInstance() =
			CurrentWeatherFragment()
	}

	private lateinit var viewModel: CurrentWeatherViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.current_weather_fragment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProvider(this).get(
			CurrentWeatherViewModel::class.java)
		// TODO: Use the ViewModel
	}

}