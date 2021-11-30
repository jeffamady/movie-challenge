package com.amadydev.alkemymoviechallenge.presentation.viewmodel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amadydev.alkemymoviechallenge.databinding.ItemMovieBinding
import com.amadydev.alkemymoviechallenge.domain.entities.Movies

class MovieAdapter(private val onMovieClickListener: OnMovieClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {
    var movies: List<Movies> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding, onMovieClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bing(this.movies[position])
    }

    override fun getItemCount(): Int = this.movies.size

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

//    }
//
//    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        val movie:Movies = movies[position]
//        holder.bing(movie)
//    }
//
//    override fun getItemCount(): Int = movies.size
}