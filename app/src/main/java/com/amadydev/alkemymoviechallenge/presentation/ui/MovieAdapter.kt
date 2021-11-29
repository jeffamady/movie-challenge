package com.amadydev.alkemymoviechallenge.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amadydev.alkemymoviechallenge.R
import com.amadydev.alkemymoviechallenge.domain.entities.Movies

class MovieAdapter(private val movies:List<Movies>):
    RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie:Movies = movies[position]
        holder.bing(movie)
    }

    override fun getItemCount(): Int = movies.size
}