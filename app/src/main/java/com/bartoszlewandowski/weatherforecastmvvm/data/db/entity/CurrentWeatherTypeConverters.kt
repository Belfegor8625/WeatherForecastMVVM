package com.bartoszlewandowski.weatherforecastmvvm.data.db.entity

import androidx.room.TypeConverter

/**
 * Created by Bartosz Lewandowski on 22.08.2020
 */
class CurrentWeatherTypeConverters {

	@TypeConverter
	fun fromListOfStrings(list: List<String>?): String? {
		return list?.joinToString()
	}

	@TypeConverter
	fun stringToList(string: String?): List<String>? {
		return string?.split(", ".toRegex())?.map { it.trim() }
	}
}