package com.amadydev.alkemymoviechallenge.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.amadydev.alkemymoviechallenge.databinding.ActivityMainBinding
import com.amadydev.alkemymoviechallenge.presentation.viewmodel.MovieAdapter
import com.amadydev.alkemymoviechallenge.presentation.viewmodel.MovieViewModel
import com.amadydev.alkemymoviechallenge.presentation.viewmodel.OnMovieClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMovieClickListener, SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding

//    private val viewModel by viewModels<MovieViewModel> {
//        ViewModelFactory(GetPopularMoviesUseCase(Repository(DataSource(DataSourceModule.profideIDataSouce(
//            DataSourceModule.profideRetrofit())))),
//            GetMovieByNameUseCase(Repository(DataSource(DataSourceModule.profideIDataSouce(
//                DataSourceModule.profideRetrofit())))))
//    }
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
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
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
        if (query.isNullOrEmpty()) Toast.makeText(this, "Can not be empty", Toast.LENGTH_SHORT)
            .show()
        if (!query.isNullOrEmpty()) {
            with(viewModel) { getMovieByName(query.toString().lowercase(),this@MainActivity) }
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = true

}