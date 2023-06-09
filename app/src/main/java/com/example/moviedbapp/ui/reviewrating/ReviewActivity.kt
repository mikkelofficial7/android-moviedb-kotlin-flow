package com.example.moviedbapp.ui.reviewrating

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.R
import com.example.moviedbapp.databinding.ActivityRatingBinding
import com.example.moviedbapp.extension.observe
import com.example.moviedbapp.model.MovieReviewResponse
import com.example.moviedbapp.base.baseview.BaseActivityVM
import com.example.moviedbapp.base.helper.EndlessRecyclerViewScrollListener
import com.example.moviedbapp.viewmodel.ReviewActivityVM

class ReviewActivity() : BaseActivityVM<ActivityRatingBinding, ReviewActivityVM>(ReviewActivityVM::class),
    Parcelable {
    private val movieId by lazy {
        intent.getLongExtra("MOVIE_ID", 0)
    }

    private val movieReviewAdapter by lazy {
        MovieReviewAdapter(this)
    }

    private var scrollListener: EndlessRecyclerViewScrollListener? = null

    constructor(parcel: Parcel) : this() {

    }

    override fun bindToolbar(): Toolbar? = viewBinding?.appbar?.toolbar

    override fun enableBackButton(): Boolean = true

    override fun getUiBinding(): ActivityRatingBinding {
        return ActivityRatingBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        viewBinding?.appbar?.tvToolbarTitle?.text = getString(R.string.review_rating)
        baseViewModel.getMovieReview(movieId, 1)
    }

    override fun initUiListener() {
        val linearLayoutManager = LinearLayoutManager(this)

        viewBinding?.rvRating?.apply {
            layoutManager = linearLayoutManager
            adapter = movieReviewAdapter
        }

        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                baseViewModel.getMovieReview(movieId, page + 1)
            }
        }

        viewBinding?.rvRating?.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)
    }

    override fun observeViewModel(viewModel: ReviewActivityVM) {
        observe(viewModel.isLoadingLiveData, ::handleLoading)
        observe(viewModel.failureLiveData, ::handleFailure)
        observe(viewModel.getMovieReviewEvent(), ::handleReview)
    }

    private fun handleReview(review: MovieReviewResponse?) {
        viewBinding?.tvNoRating?.isVisible = review?.results?.isEmpty() == true

        if(review?.page == 1) movieReviewAdapter.clearData()

        review?.results?.let {
            if(it.isEmpty()) return@let
            movieReviewAdapter.setData(it)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReviewActivity> {
        override fun createFromParcel(parcel: Parcel): ReviewActivity {
            return ReviewActivity(parcel)
        }

        override fun newArray(size: Int): Array<ReviewActivity?> {
            return arrayOfNulls(size)
        }
    }
}