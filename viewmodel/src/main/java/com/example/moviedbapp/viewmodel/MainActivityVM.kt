package com.example.moviedbapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviedbapp.model.GenreResponse
import com.example.moviedbapp.base.baseview.BaseViewModel
import com.example.moviedbapp.base.extension.getGeneralError
import com.example.moviedbapp.base.helper.NetworkHandler
import com.example.moviedbapp.viewmodel.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivityVM(networkHandler: NetworkHandler, val movieUseCase: MovieUseCase) : BaseViewModel(networkHandler) {
    private val _genreEvent = MutableLiveData<GenreResponse>()
    fun getGenreEvent(): MutableLiveData<GenreResponse> = _genreEvent

    fun getAllMovieGenre() {
        isLoadingLiveData.postValue(true)

        executeJob {
            safeScopeFun {
                Log.d("TAF", "AAAaa ${it.message}")
                handleFailure(it.getGeneralError())
            }.launch(Dispatchers.IO) {
                movieUseCase.getAllMovieGenre()
                    .collectLatest {
                        isLoadingLiveData.postValue(false)
                        _genreEvent.postValue(it)
                    }
            }
        }
    }
}