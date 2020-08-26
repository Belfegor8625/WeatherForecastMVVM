package com.bartoszlewandowski.weatherforecastmvvm.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

/**
 * Created by Bartosz Lewandowski on 25.08.2020
 */
abstract class PreferenceProvider(context: Context) {
	private val appContext = context.applicationContext

	protected val preferences: SharedPreferences
		get() = PreferenceManager.getDefaultSharedPreferences(appContext)
}