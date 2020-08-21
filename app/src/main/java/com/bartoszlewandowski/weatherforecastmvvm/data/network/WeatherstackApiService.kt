package com.bartoszlewandowski.weatherforecastmvvm.data.network

import android.content.Context
import com.bartoszlewandowski.weatherforecastmvvm.data.network.response.CurrentWeatherResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Bartosz Lewandowski on 18.08.2020
 */

const val API_KEY = "62f2fba7498cfa926492e93a589b291c"

interface WeatherstackApiService {

	@GET("current")
	suspend fun getCurrentWeather(
		@Query("access_key") accessKey: String = API_KEY,
		@Query("query") location: String,
		@Query("units") units: String
	): CurrentWeatherResponse

	companion object {
		operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): WeatherstackApiService {
			val requestInterceptor = Interceptor { chain ->

				val url = chain.request()
					.url()
					.newBuilder()
					.build()
				val request = chain.request()
					.newBuilder()
					.url(url)
					.build()
				return@Interceptor chain.proceed(request)
			}
			val okHttpClient = OkHttpClient.Builder()
				.addInterceptor(requestInterceptor)
				.addInterceptor(connectivityInterceptor)
				.build()

			return Retrofit.Builder()
				.client(okHttpClient)
				.baseUrl("http://api.weatherstack.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build()
				.create(WeatherstackApiService::class.java)
		}
	}
}