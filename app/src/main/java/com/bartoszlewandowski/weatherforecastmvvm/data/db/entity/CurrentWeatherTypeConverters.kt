package com.bartoszlewandowski.weatherforecastmvvm.data.db.entity

import androidx.room.TypeConverter

/**
 * Created by Bartosz Lewandowski on 22.08.2020
 */
object CurrentWeatherTypeConverters {

	@TypeConverter
	fun fromListOfStrings(list: List<String>?) = list?.joinToString()

	@TypeConverter
	fun stringToList(string: String?) = string?.let {
		it.split(", ".toRegex()).map { element -> element.trim() }
	}
}