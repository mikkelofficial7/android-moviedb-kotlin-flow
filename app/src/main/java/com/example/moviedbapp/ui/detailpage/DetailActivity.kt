package com.example.moviedbapp.ui.detailpage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.base.BaseActivityVM
import com.example.moviedbapp.databinding.ActivityDetailBinding
import com.example.moviedbapp.extension.observe
import com.example.moviedbapp.helper.EndlessRecyclerViewScrollListener
import com.example.moviedbapp.model.response.MovieGenreResponse
import com.example.moviedbapp.ui.detailmovie.DetailMovieActivity


class DetailActivity: BaseActivityVM<ActivityDetailBinding, DetailActivityVM>(DetailActivityVM::class) {
    private val movieAdapter by lazy {
        MovieAdapter(this)
    }
    private var scrollListener: EndlessRecyclerViewScrollListener? = null

    private val title: String by lazy {
        intent.getStringExtra("GENRE_NAME").orEmpty()
    }

    private val idGenre: Int by lazy {
        intent.getIntExtra("GENRE_ID", 0)
    }

    override fun bindToolbar(): Toolbar? = viewBinding?.appbar?.toolbar

    override fun enableBackButton(): Boolean = true

    override fun getUiBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        viewBinding?.appbar?.tvToolbarTitle?.text = title
        baseViewModel.getMovieByGenre(idGenre, 1)
    }

    override fun initUiListener() {
        val linearLayoutManager = LinearLayoutManager(this@DetailActivity)
        viewBinding?.rvMovie?.apply {
            layoutManager = linearLayoutManager
            adapter = movieAdapter
        }

        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                baseViewModel.getMovieByGenre(idGenre, page + 1)
            }
        }

        viewBinding?.rvMovie?.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)

        movieAdapter.onClick = {
            navigateToMovieDetail(it)
        }
    }

    override fun observeViewModel(viewModel: DetailActivityVM) {
        observe(viewModel.isLoadingLiveData, ::handleLoading)
        observe(viewModel.failureLiveData, ::handleFailure)
        observe(viewModel.getMovieListEvent(), ::handleMovieList)
    }

    private fun handleMovieList(movie: MovieGenreResponse?) {
        if(movie?.page == 1) movieAdapter.clearData()

        movie?.results?.let {
            if(it.isEmpty()) return@let

            viewBinding?.tvNoData?.isVisible = false
            movieAdapter.setData(it)
        }
    }

    private fun navigateToMovieDetail(id: Long) {
        startActivity(Intent(this, DetailMovieActivity::class.java).apply {
            putExtra("MOVIE_ID", id)
        })
    }
}