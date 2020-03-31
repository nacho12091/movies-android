package com.raywenderlich.movies.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.movies.R
import com.raywenderlich.movies.models.Movie
import com.squareup.picasso.Picasso

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
    R.layout.fragment_movie_cell, parent, false)) {

    private var mTitleView: TextView? = null
    private var mReleaseDate: TextView? = null
    private var mImageView: ImageView? = null
    private var mDescription: TextView? = null

    init {
        mTitleView = itemView.findViewById(R.id.movie_title)
        mReleaseDate = itemView.findViewById(R.id.movie_release_date)
        mImageView = itemView.findViewById(R.id.movie_image)
        mDescription = itemView.findViewById(R.id.movie_description)
    }

    fun bind(movie: Movie) {
        mTitleView?.text = movie.original_title
        mReleaseDate?.text = movie.release_date
        mDescription?.text = movie.overview
        Picasso.get().load(movie.poster_path).into(mImageView)
    }
}