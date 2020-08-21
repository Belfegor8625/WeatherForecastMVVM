package com.bartoszlewandowski.weatherforecastmvvm.data.network.response

import com.bartoszlewandowski.weatherforecastmvvm.data.db.entity.CurrentWeatherEntry
import com.bartoszlewandowski.weatherforecastmvvm.data.db.entity.Location
import com.bartoszlewandowski.weatherforecastmvvm.data.db.entity.Request
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
	@SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
	val location: Location,
	val request: Request
)