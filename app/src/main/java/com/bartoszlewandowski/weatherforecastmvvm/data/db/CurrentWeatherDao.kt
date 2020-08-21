package com.bartoszlewandowski.weatherforecastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bartoszlewandowski.weatherforecastmvvm.data.db.entity.CURRENT_WEATHER_ID
import com.bartoszlewandowski.weatherforecastmvvm.data.db.entity.CurrentWeatherEntry
import com.bartoszlewandowski.weatherforecastmvvm.data.db.unitlocalized.ImperialCurrentWeatherEntry
import com.bartoszlewandowski.weatherforecastmvvm.data.db.unitlocalized.MetricCurrentWeatherEntry

/**
 * Created by Bartosz Lewandowski on 21.08.2020
 */

@Dao
interface CurrentWeatherDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun upsert(weatherEntry: CurrentWeatherEntry)

	@Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
	fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

	@Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
	fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
}