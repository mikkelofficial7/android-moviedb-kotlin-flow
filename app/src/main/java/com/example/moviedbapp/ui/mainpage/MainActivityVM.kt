package com.example.moviedbapp.ui.mainpage

import androidx.lifecycle.MutableLiveData
import com.example.moviedbapp.base.BaseViewModel
import com.example.moviedbapp.extension.getGeneralError
import com.example.moviedbapp.helper.NetworkHandler
import com.example.moviedbapp.model.response.GenreResponse
import com.example.moviedbapp.usecase.MovieUseCase
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