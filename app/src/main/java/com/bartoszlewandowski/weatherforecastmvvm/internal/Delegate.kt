package com.bartoszlewandowski.weatherforecastmvvm.internal

import kotlinx.coroutines.*

/**
 * Created by Bartosz Lewandowski on 22.08.2020
 */

fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
	return lazy {
		GlobalScope.async(start = CoroutineStart.LAZY) {
			block.invoke(this)
		}
	}
}