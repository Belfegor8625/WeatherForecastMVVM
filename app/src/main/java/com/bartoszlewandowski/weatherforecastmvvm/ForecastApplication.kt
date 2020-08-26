package com.bartoszlewandowski.weatherforecastmvvm

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.bartoszlewandowski.weatherforecastmvvm.data.db.ForecastDatabase
import com.bartoszlewandowski.weatherforecastmvvm.data.network.*
import com.bartoszlewandowski.weatherforecastmvvm.data.provider.LocationProvider
import com.bartoszlewandowski.weatherforecastmvvm.data.provider.LocationProviderImpl
import com.bartoszlewandowski.weatherforecastmvvm.data.provider.UnitProvider
import com.bartoszlewandowski.weatherforecastmvvm.data.provider.UnitProviderImpl
import com.bartoszlewandowski.weatherforecastmvvm.data.repository.ForecastRepository
import com.bartoszlewandowski.weatherforecastmvvm.data.repository.ForecastRepositoryImpl
import com.bartoszlewandowski.weatherforecastmvvm.ui.weather.current.CurrentWeatherViewModelFactory
import com.google.android.gms.location.LocationServices
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
		bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }
		bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
		bind() from singleton { WeatherstackApiService(instance()) }
		bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
		bind() from provider {
			LocationServices.getFusedLocationProviderClient(instance<Context>())
		}
		bind<LocationProvider>() with singleton { LocationProviderImpl(instance(), instance()) }
		bind<ForecastRepository>() with singleton {
			ForecastRepositoryImpl(instance(), instance(), instance(), instance())
		}
		bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
		bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
	}

	override fun onCreate() {
		super.onCreate()
		AndroidThreeTen.init(this)
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
	}
}