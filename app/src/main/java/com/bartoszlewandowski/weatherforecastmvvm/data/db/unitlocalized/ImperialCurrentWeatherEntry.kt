package com.bartoszlewandowski.weatherforecastmvvm.data.db.unitlocalized

/**
 * Created by Bartosz Lewandowski on 21.08.2020
 */
data class ImperialCurrentWeatherEntry(
	override val unit: String,
	override val temperature: Int,
	override val windSpeed: Int,
	override val cloudcover: Int,
	override val feelslike: Int,
	override val humidity: Int,
	override val isDay: String,
	override val observationTime: String,
	override val precip: Double,
	override val pressure: Int,
	override val uvIndex: Int,
	override val visibility: Int,
	override val weatherCode: Int,
	override val weatherDescriptions: List<String>,
	override val weatherIcons: List<String>,
	override val windDegree: Int,
	override val windDir: String) : UnitSpecificCurrentWeatherEntry