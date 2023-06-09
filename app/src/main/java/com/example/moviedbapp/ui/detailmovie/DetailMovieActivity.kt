package com.example.moviedbapp.ui.detailmovie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviedbapp.BuildConfig
import com.example.moviedbapp.R
import com.example.moviedbapp.base.constant.DateConstant
import com.example.moviedbapp.databinding.ActivityDetailMovieBinding
import com.example.moviedbapp.extension.changeDateFormat
import com.example.moviedbapp.extension.observe
import com.example.moviedbapp.model.MovieDetailResponse
import com.example.moviedbapp.model.MovieVideoResponse
import com.example.moviedbapp.base.baseview.BaseActivityVM
import com.example.moviedbapp.ui.bottomsheet.VideoLoadBottomSheet
import com.example.moviedbapp.ui.reviewrating.ReviewActivity
import com.example.moviedbapp.viewmodel.DetailMovieVM

class DetailMovieActivity : BaseActivityVM<ActivityDetailMovieBinding, DetailMovieVM>(DetailMovieVM::class) {
    private val movieId by lazy {
        intent.getLongExtra("MOVIE_ID", 0)
    }

    private val movieVideoAdapter by lazy {
        MovieVideoAdapter(this)
    }

    override fun bindToolbar(): Toolbar? = viewBinding?.appbar?.toolbar

    override fun enableBackButton(): Boolean = true

    override fun getUiBinding(): ActivityDetailMovieBinding {
        return ActivityDetailMovieBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        baseViewModel.getMovieDetail(movieId)
        baseViewModel.getMovieVideoDetail(movieId)
    }

    override fun initUiListener() {
        viewBinding?.rvTrailer?.apply {
            layoutManager = LinearLayoutManager(this@DetailMovieActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = movieVideoAdapter
        }

        movieVideoAdapter.onClick = {
            loadMovieTrailer(it)
        }

        viewBinding?.tvSeeReview?.setOnClickListener {
            navigateReviewPage()
        }
    }

    override fun observeViewModel(viewModel: DetailMovieVM) {
        observe(viewModel.isLoadingLiveData, ::handleLoading)
        observe(viewModel.failureLiveData, ::handleFailure)
        observe(viewModel.getMovieDetailEvent(), ::handleMovieDetail)
        observe(viewModel.getMovieVideoEvent(), ::handleMovieVideo)
    }

    private fun handleMovieDetail(detail: MovieDetailResponse?) {
        detail?.let { detail ->
            viewBinding?.apply {
                appbar.tvToolbarTitle.text = detail.title

                Glide.with(this@DetailMovieActivity).load("${BuildConfig.BASE_IMAGE_URL}${detail.backdrop_path}").into(ivBackdrop)
                Glide.with(this@DetailMovieActivity).load("${BuildConfig.BASE_IMAGE_URL}${detail.poster_path}").into(ivPoster)

                tvTitle.text = detail.title
                tvReleaseDate.text = getString(R.string.release_on, detail.release_date.changeDateFormat(
                    DateConstant.DATE_DEFAULT, DateConstant.DATE))
                tvPopularity.text = getString(R.string.popularity, detail.popularity.toString())
                tvReleaseStatus.text = detail.status

                tvOverview.text = detail.overview
                tvVoteAverage.text = getString(R.string.vote_average, detail.vote_average.toString(), detail.vote_count.toString())
            }
        }
    }

    private fun handleMovieVideo(video: MovieVideoResponse?) {
        if(video?.results?.isEmpty() == true) {
            viewBinding?.rvTrailer?.isVisible = false
            viewBinding?.tvNoVideo?.isVisible = true
        } else {
            viewBinding?.rvTrailer?.isVisible = true
            viewBinding?.tvNoVideo?.isVisible = false

            video?.results?.let {
                if(it.isEmpty()) return@let
                movieVideoAdapter.setData(it)
            }
        }
    }

    private fun navigateReviewPage() {
        startActivity(Intent(this, ReviewActivity::class.java).apply {
            putExtra("MOVIE_ID", movieId)
        })
    }

    private fun loadMovieTrailer(linkKey: String) {
        VideoLoadBottomSheet(linkKey).show(supportFragmentManager, VideoLoadBottomSheet::class.java.simpleName)
    }
}