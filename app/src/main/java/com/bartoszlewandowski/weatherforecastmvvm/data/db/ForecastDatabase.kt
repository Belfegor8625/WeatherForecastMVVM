package com.bartoszlewandowski.weatherforecastmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bartoszlewandowski.weatherforecastmvvm.data.db.entity.CurrentWeatherEntry
import com.bartoszlewandowski.weatherforecastmvvm.data.db.entity.CurrentWeatherTypeConverters

/**
 * Created by Bartosz Lewandowski on 21.08.2020
 */

@Database(
	entities = [CurrentWeatherEntry::class],
	version = 2
)
@TypeConverters(CurrentWeatherTypeConverters::class)
abstract class ForecastDatabase : RoomDatabase() {
	abstract fun currentWeatherDao(): CurrentWeatherDao

	companion object {
		@Volatile
		private var instance: ForecastDatabase? = null
		private val LOCK = Any()

		operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
			instance ?: buildDatabase(context).also { instance = it }
		}

		private fun buildDatabase(context: Context) =
			Room.databaseBuilder(context.applicationContext, ForecastDatabase::class.java,
				"forecast.db").build()
	}
}