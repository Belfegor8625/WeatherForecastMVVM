package com.bartoszlewandowski.weatherforecastmvvm.internal

import java.io.IOException
import java.lang.Exception

/**
 * Created by Bartosz Lewandowski on 21.08.2020
 */

class NoConnectivityException : IOException()
class LocationPermissionNotGrantedException: Exception()