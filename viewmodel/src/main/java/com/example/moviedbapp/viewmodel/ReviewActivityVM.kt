package com.example.moviedbapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.moviedbapp.model.MovieReviewResponse
import com.example.moviedbapp.base.baseview.BaseViewModel
import com.example.moviedbapp.base.extension.getGeneralError
import com.example.moviedbapp.base.helper.NetworkHandler
import com.example.moviedbapp.viewmodel.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ReviewActivityVM(networkHandler: NetworkHandler, val movieUseCase: MovieUseCase) : BaseViewModel(networkHandler) {
    private val _movieReviewEvent = MutableLiveData<MovieReviewResponse>()
    fun getMovieReviewEvent(): MutableLiveData<MovieReviewResponse> = _movieReviewEvent

    fun getMovieReview(id: Long, page: Int) {
        isLoadingLiveData.postValue(true)

        executeJob {
            safeScopeFun {
                handleFailure(it.getGeneralError())
            }.launch(Dispatchers.IO) {
                movieUseCase.getMovieReview(id.toInt(), page).collectLatest {
                    isLoadingLiveData.postValue(false)
                    _movieReviewEvent.postValue(it)
                }
            }
        }
    }
}