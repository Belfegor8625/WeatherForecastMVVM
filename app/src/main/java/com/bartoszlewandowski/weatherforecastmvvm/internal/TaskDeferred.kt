package com.bartoszlewandowski.weatherforecastmvvm.internal

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

/**
 * Created by Bartosz Lewandowski on 26.08.2020
 */

fun <T> Task<T>.asDeferred(): Deferred<T> {
	val deferred = CompletableDeferred<T>()

	this.addOnSuccessListener { result ->
		deferred.complete(result)
	}
	this.addOnFailureListener { exception ->
		deferred.completeExceptionally(exception)
	}

	return deferred
}