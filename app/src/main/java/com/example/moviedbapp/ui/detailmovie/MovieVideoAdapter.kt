package com.example.moviedbapp.ui.detailmovie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbapp.BuildConfig
import com.example.moviedbapp.R
import com.example.moviedbapp.databinding.ItemVideoBinding
import com.example.moviedbapp.model.response.Video

class MovieVideoAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mList: List<Video> = listOf()
    internal var onClick: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolderVideo(context, binding, onClick)
    }

    fun setData(movieResult: List<Video>) {
        mList = movieResult
        notifyItemInserted(0)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderVideo).bind(mList[position], position)
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    class ViewHolderVideo(private val context: Context, private val itemBinding: ItemVideoBinding, private val onClick: (String) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(video: Video, position: Int) {
            Glide.with(context).load("https://img.youtube.com/vi/${video.key}/hqdefault.jpg").into(itemBinding.ivTrailer)
            itemBinding.tvTrailerName.text = video.name
            itemBinding.tvTrailerSource.text = context.getString(R.string.from, video.site)

            itemBinding.root.setOnClickListener {
                onClick(video.key)
            }
        }
    }
}