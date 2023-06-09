package com.example.moviedbapp.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

suspend fun <T : Any> flowOnValue(value : T) : Flow<T> {
    return  flow {emit(value) }.flowOn(Dispatchers.IO)
}

fun <T : Any, L : MutableLiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))