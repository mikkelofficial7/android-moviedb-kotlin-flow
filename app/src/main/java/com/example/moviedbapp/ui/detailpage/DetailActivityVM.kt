package com.example.moviedbapp.ui.detailpage

import androidx.lifecycle.MutableLiveData
import com.example.moviedbapp.base.BaseViewModel
import com.example.moviedbapp.extension.getGeneralError
import com.example.moviedbapp.helper.NetworkHandler
import com.example.moviedbapp.model.response.MovieGenreResponse
import com.example.moviedbapp.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailActivityVM (networkHandler: NetworkHandler, val movieUseCase: MovieUseCase) : BaseViewModel(networkHandler) {
    private val _movieListEvent = MutableLiveData<MovieGenreResponse>()
    fun getMovieListEvent(): MutableLiveData<MovieGenreResponse> = _movieListEvent

    fun getMovieByGenre(idGenre: Int, page: Int) {
        isLoadingLiveData.postValue(true)

        executeJob {
            safeScopeFun {
                handleFailure(it.getGeneralError())
            }.launch(Dispatchers.IO) {
                movieUseCase.getMovieByGenre(idGenre, page)
                    .collectLatest {
                        isLoadingLiveData.postValue(false)
                        _movieListEvent.postValue(it)
                    }
            }
        }
    }
}