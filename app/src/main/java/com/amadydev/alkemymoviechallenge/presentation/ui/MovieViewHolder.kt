package com.amadydev.alkemymoviechallenge.presentation.ui

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.amadydev.alkemymoviechallenge.data.DB
import com.amadydev.alkemymoviechallenge.databinding.ActivityMainBinding
import com.amadydev.alkemymoviechallenge.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bing(movie: MovieModel) {
        Picasso.get().load(DB.base_img_url + movie.posterPath).into(binding.ivMovie)
        binding.tvName.text = movie.originalTitle.uppercase()
    }

}