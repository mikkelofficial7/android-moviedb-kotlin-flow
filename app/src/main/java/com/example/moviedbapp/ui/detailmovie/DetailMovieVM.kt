package com.example.moviedbapp.ui.detailmovie

import androidx.lifecycle.MutableLiveData
import com.example.moviedbapp.base.BaseViewModel
import com.example.moviedbapp.extension.getGeneralError
import com.example.moviedbapp.helper.NetworkHandler
import com.example.moviedbapp.model.response.MovieDetailResponse
import com.example.moviedbapp.model.response.MovieReviewResponse
import com.example.moviedbapp.model.response.MovieVideoResponse
import com.example.moviedbapp.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailMovieVM(networkHandler: NetworkHandler, val movieUseCase: MovieUseCase) : BaseViewModel(networkHandler) {
    private val _movieDetailEvent = MutableLiveData<MovieDetailResponse>()
    fun getMovieDetailEvent(): MutableLiveData<MovieDetailResponse> = _movieDetailEvent

    private val _movieVideoEvent = MutableLiveData<MovieVideoResponse>()
    fun getMovieVideoEvent(): MutableLiveData<MovieVideoResponse> = _movieVideoEvent

    fun getMovieDetail(id: Long) {
        isLoadingLiveData.postValue(true)

        executeJob {
            safeScopeFun {
                handleFailure(it.getGeneralError())
            }.launch(Dispatchers.IO) {
                movieUseCase.getMovieDetail(id.toInt()).collectLatest {
                    isLoadingLiveData.postValue(false)
                    _movieDetailEvent.postValue(it)
                }
            }
        }
    }

    fun getMovieVideoDetail(id: Long) {
        isLoadingLiveData.postValue(true)

        executeJob {
            safeScopeFun {
                handleFailure(it.getGeneralError())
            }.launch(Dispatchers.IO) {
                movieUseCase.getMovieVideo(id.toInt()).collectLatest {
                    isLoadingLiveData.postValue(false)
                    _movieVideoEvent.postValue(it)
                }
            }
        }
    }
}