package com.bartoszlewandowski.weatherforecastmvvm

import android.app.Application
import com.bartoszlewandowski.weatherforecastmvvm.data.db.ForecastDatabase
import com.bartoszlewandowski.weatherforecastmvvm.data.network.*
import com.bartoszlewandowski.weatherforecastmvvm.data.repository.ForecastRepository
import com.bartoszlewandowski.weatherforecastmvvm.data.repository.ForecastRepositoryImpl
import com.bartoszlewandowski.weatherforecastmvvm.ui.weather.current.CurrentWeatherViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

/**
 * Created by Bartosz Lewandowski on 22.08.2020
 */
class ForecastApplication : Application(), DIAware {
	override val di = DI.lazy {
		import(androidXModule(this@ForecastApplication))

		bind() from singleton { ForecastDatabase(instance()) }
		bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
		bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
		bind() from singleton { WeatherstackApiService(instance()) }
		bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
		bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance()) }
		bind() from provider { CurrentWeatherViewModelFactory(instance()) }
	}

	override fun onCreate() {
		super.onCreate()
		AndroidThreeTen.init(this)
	}
}