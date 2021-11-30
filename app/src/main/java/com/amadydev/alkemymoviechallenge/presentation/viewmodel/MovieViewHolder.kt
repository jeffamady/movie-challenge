package com.amadydev.alkemymoviechallenge.presentation.viewmodel

import androidx.recyclerview.widget.RecyclerView
import com.amadydev.alkemymoviechallenge.di.DB
import com.amadydev.alkemymoviechallenge.databinding.ItemMovieBinding
import com.amadydev.alkemymoviechallenge.domain.entities.Movies
import com.squareup.picasso.Picasso

class MovieViewHolder(private val binding: ItemMovieBinding, private val onMovieClickListener: OnMovieClickListener) : RecyclerView.ViewHolder(binding.root) {

    fun bing(movie: Movies) {
        with(binding){
            Picasso.get().load(DB.base_img_url + movie.posterPath).into(ivMovie)
            tvName.text = movie.originalTitle.uppercase()
            root.setOnClickListener{
                onMovieClickListener.onMovieClicked(adapterPosition)
            }
        }
    }

}