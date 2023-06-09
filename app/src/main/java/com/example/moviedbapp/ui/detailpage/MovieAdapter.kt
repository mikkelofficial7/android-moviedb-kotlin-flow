package com.example.moviedbapp.ui.detailpage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbapp.BuildConfig
import com.example.moviedbapp.R
import com.example.moviedbapp.base.constant.DateConstant
import com.example.moviedbapp.databinding.ItemMovieBinding
import com.example.moviedbapp.extension.changeDateFormat
import com.example.moviedbapp.model.Movie

class MovieAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mList: ArrayList<Movie> = arrayListOf()
    internal var onClick: (Long) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolderMovie(context, binding, onClick)
    }

    fun clearData() {
        mList.clear()
    }

    fun setData(movieResult: List<Movie>) {
        mList.addAll(movieResult)
        notifyItemInserted(0)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderMovie).bind(mList[position], position)
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    class ViewHolderMovie(private val context: Context, private val itemBinding: ItemMovieBinding, private val onClick: (Long) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie: Movie, position: Int) {
            Glide.with(itemBinding.root).load("${BuildConfig.BASE_IMAGE_URL}${movie.poster_path}").placeholder(R.drawable.ic_no_image).into(itemBinding.ivMovie)
            Glide.with(itemBinding.root).load("${BuildConfig.BASE_IMAGE_URL}${movie.backdrop_path}").into(itemBinding.ivMovieBackdrop)

            itemBinding.tvTitle.text = movie.title
            itemBinding.tvReleaseDate.text = context.getString(R.string.release_on, movie.release_date.changeDateFormat(
                DateConstant.DATE_DEFAULT, DateConstant.DATE))
            itemBinding.tvPopularity.text = context.getString(R.string.popularity, movie.popularity.toString())

            itemBinding.layoutMovie.setOnClickListener {
                onClick(movie.id)
            }
        }
    }
}