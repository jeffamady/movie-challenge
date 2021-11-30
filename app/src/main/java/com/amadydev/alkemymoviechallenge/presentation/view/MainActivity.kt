package com.amadydev.alkemymoviechallenge.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.amadydev.alkemymoviechallenge.databinding.ActivityMainBinding
import com.amadydev.alkemymoviechallenge.presentation.viewmodel.MovieAdapter
import com.amadydev.alkemymoviechallenge.presentation.viewmodel.MovieViewModel
import com.amadydev.alkemymoviechallenge.presentation.viewmodel.OnMovieClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMovieClickListener, SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding


    private val viewModel: MovieViewModel by viewModels()

    private val movieAdapter: MovieAdapter by lazy { MovieAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callViewModelMethods()
        onClickEvents()
        initRecycler()
        viewModelObservers()
    }

    private fun callViewModelMethods() {
        viewModel.getMovies(this)
    }

    private fun onClickEvents() {
        binding.svMovies.setOnQueryTextListener(this)
    }

    private fun initRecycler() {
        with(binding.rvMovies) {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = movieAdapter
        }
    }

    private fun viewModelObservers() {
        viewModel.list.observe(this, {
            movieAdapter.movies = it
        })

        viewModel.onError.observe(this, {
            val errorText =
                if (!it.isNullOrEmpty()) it else "It seems we don't have this movie or something went wrong. Please try again!"
            Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
        })

        viewModel.isLoading.observe(this, {
            binding.mainProgress.isVisible = it
        })
    }

    override fun onMovieClicked(position: Int) {
//        Toast.makeText(this, "Movie $position clicked", Toast.LENGTH_SHORT).show()
        val movie = movieAdapter.movies[position]
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("INTENT_ID", movie.id)
        intent.putExtra("INTENT_TITLE", movie.originalTitle)
        intent.putExtra("INTENT_POSTER_PATH", movie.posterPath)
        intent.putExtra("INTENT_BACKDROP_PATH", movie.backdropPath)
        intent.putExtra("INTENT_LANGUAGE", movie.originalLanguage)
        intent.putExtra("INTENT_OVERVIEW", movie.overview)
        intent.putExtra("INTENT_VOTE_AVERAGE", movie.voteAverage)
        intent.putExtra("INTENT_POPULARITY", movie.popularity)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        hideKeyboard()
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty()) {
            with(viewModel) { getMovieByName(newText, this@MainActivity) }
        }
        return true
    }

}