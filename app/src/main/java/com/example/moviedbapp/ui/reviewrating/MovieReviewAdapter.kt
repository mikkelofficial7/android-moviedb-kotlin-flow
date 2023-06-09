package com.example.moviedbapp.ui.reviewrating

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbapp.BuildConfig
import com.example.moviedbapp.R
import com.example.moviedbapp.base.constant.DateConstant
import com.example.moviedbapp.databinding.ItemReviewBinding
import com.example.moviedbapp.extension.changeDateFormat
import com.example.moviedbapp.model.Review

class MovieReviewAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mList: ArrayList<Review> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolderReview(context, binding)
    }

    fun clearData() {
        mList.clear()
    }

    fun setData(movieResult: List<Review>) {
        mList.addAll(movieResult)
        notifyItemInserted(0)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderReview).bind(mList[position], position)
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    class ViewHolderReview(private val context: Context, private val itemBinding: ItemReviewBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(review: Review, position: Int) {
            itemBinding.apply {
                Glide.with(context).load("${BuildConfig.BASE_IMAGE_URL}${review.author_details.avatar_path}").into(ivAvatar)
                tvName.text = review.author.ifEmpty { review.author_details.username }
                tvCreatedDate.text = context.getString(R.string.posted_at, review.created_at.changeDateFormat(
                    DateConstant.ZONED_DATE, DateConstant.DATE_TIME))
                tvContent.text = review.content
                tvRating.text = context.getString(R.string.rating, (review.author_details.rating ?: 0).toString())
            }
        }
    }
}