package com.bartoszlewandowski.weatherforecastmvvm.data.db.unitlocalized

/**
 * Created by Bartosz Lewandowski on 21.08.2020
 */
interface UnitSpecificCurrentWeatherEntry {
	val unit: String?
	val cloudcover: Int
	val feelslike: Int
	val humidity: Int
	val isDay: String
	val observationTime: String
	val precip: Double
	val pressure: Int
	val temperature: Int
	val uvIndex: Int
	val visibility: Int
	val weatherCode: Int
	val weatherDescriptions: List<String>
	val weatherIcons: List<String>
	val windDegree: Int
	val windDir: String
	val windSpeed: Int
}