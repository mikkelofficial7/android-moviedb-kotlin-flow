package com.example.moviedbapp.ui.mainpage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.databinding.ItemGenreBinding
import com.example.moviedbapp.model.response.Genre

class GenreAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mList: List<Genre> = listOf()
    internal var onClick: (Genre) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemGenreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolderGenre(context, binding, onClick)
    }

    fun setData(list: List<Genre>) {
        mList = list
        notifyItemInserted(0)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderGenre).bind(mList[position], position)
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    class ViewHolderGenre(private val context: Context, private val itemBinding: ItemGenreBinding, private val onClick: (Genre) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(genre: Genre, position: Int) {
            itemBinding.genreName.text = genre.name
            itemBinding.layoutGenre.setOnClickListener {
                onClick(genre)
            }
        }
    }
}