package com.amadydev.alkemymoviechallenge.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amadydev.alkemymoviechallenge.R
import com.amadydev.alkemymoviechallenge.data.TMDB
import com.amadydev.alkemymoviechallenge.databinding.ItemMovieBinding
import com.amadydev.alkemymoviechallenge.domain.entities.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(private val onMovieClickListener: OnMovieClickListener) :
    PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bing(currentItem)
        }

    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val movie = getItem(position)
                    if (movie != null) {
                        onMovieClickListener.onMovieClicked(movie)
                    }
                }
            }
        }

        fun bing(movie: Movie) {
            with(binding) {
                Picasso
                    .get()
                    .load(TMDB.base_img_url + movie.posterPath)
                    .error(R.drawable.ic_error)
                    .into(ivMovie)
                tvName.text = movie.originalTitle.uppercase()
//                root.setOnClickListener{
//                    onMovieClickListener.onMovieClicked(adapterPosition)
//                }
            }
        }
    }

    interface OnMovieClickListener {
        fun onMovieClicked(movie: Movie)
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}