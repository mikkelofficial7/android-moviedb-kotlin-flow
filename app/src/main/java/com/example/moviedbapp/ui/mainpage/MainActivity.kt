package com.example.moviedbapp.ui.mainpage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedbapp.databinding.ActivityMainBinding
import com.example.moviedbapp.extension.observe
import com.example.moviedbapp.model.Genre
import com.example.moviedbapp.model.GenreResponse
import com.example.moviedbapp.base.baseview.BaseActivityVM
import com.example.moviedbapp.ui.detailpage.DetailActivity
import com.example.moviedbapp.viewmodel.MainActivityVM

class MainActivity : BaseActivityVM<ActivityMainBinding, MainActivityVM>(MainActivityVM::class) {
    private val genreAdapter by lazy {
        GenreAdapter(this)
    }

    override fun bindToolbar(): Toolbar? = null

    override fun enableBackButton(): Boolean = true

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        baseViewModel.getAllMovieGenre()
    }

    override fun initUiListener() {
        viewBinding?.rvGenre?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = genreAdapter
        }

        genreAdapter.onClick = {
            navigateToMovieList(it)
        }
    }

    override fun observeViewModel(viewModel: MainActivityVM) {
        observe(viewModel.isLoadingLiveData, ::handleLoading)
        observe(viewModel.failureLiveData, ::handleFailure)
        observe(viewModel.getGenreEvent(), ::handleAllGenre)
    }

    override fun getUiBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun handleAllGenre(genres: GenreResponse?) {
        genres?.genres?.let {
            if(it.isEmpty()) return@let
            genreAdapter.setData(it)
        }
    }

    private fun navigateToMovieList(genre: Genre) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra("GENRE_ID", genre.id)
            putExtra("GENRE_NAME", genre.name)
        })
    }
}