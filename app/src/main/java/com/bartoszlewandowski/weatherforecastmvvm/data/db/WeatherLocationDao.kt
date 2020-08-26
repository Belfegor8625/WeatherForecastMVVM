package com.bartoszlewandowski.weatherforecastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bartoszlewandowski.weatherforecastmvvm.data.db.entity.WEATHER_LOCATION_ID
import com.bartoszlewandowski.weatherforecastmvvm.data.db.entity.WeatherLocation

/**
 * Created by Bartosz Lewandowski on 25.08.2020
 */
@Dao
interface WeatherLocationDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun upsert(weatherLocation: WeatherLocation)

	@Query("select * from weather_location where id = $WEATHER_LOCATION_ID")
	fun getLocation(): LiveData<WeatherLocation>
}