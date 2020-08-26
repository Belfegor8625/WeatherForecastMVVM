package com.bartoszlewandowski.weatherforecastmvvm.data.provider

import com.bartoszlewandowski.weatherforecastmvvm.internal.UnitSystem

/**
 * Created by Bartosz Lewandowski on 24.08.2020
 */
interface UnitProvider {
	fun getUnitSystem(): UnitSystem
}