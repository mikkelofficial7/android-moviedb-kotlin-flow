package com.example.moviedbapp.base.baseview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbapp.base.exception.Failure
import com.example.moviedbapp.base.helper.NetworkHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.plus

abstract class BaseViewModel(
    private val networkHandler: NetworkHandler
) : ViewModel() {
    val failureLiveData: MutableLiveData<Failure> = MutableLiveData()

    val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun handleFailure(failure: Failure) {
        isLoadingLiveData.postValue( false)
        failureLiveData.postValue(failure)
    }

    protected fun executeJob(invoke: () -> Unit) {
        when (networkHandler.isNetworkAvailable()) {
            true -> invoke()
            else -> handleFailure(Failure.NetworkConnection)
        }
    }

    protected fun safeScopeFun(error :(Throwable) -> Unit) : CoroutineScope{
      return viewModelScope + CoroutineExceptionHandler { coroutineContext, throwable ->
            error.invoke(throwable)
        }
    }
}
