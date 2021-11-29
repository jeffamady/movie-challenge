package com.amadydev.alkemymoviechallenge.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.amadydev.alkemymoviechallenge.data.remote.datasource.DataSource
import com.amadydev.alkemymoviechallenge.data.remote.repository.Repository
import com.amadydev.alkemymoviechallenge.databinding.ActivityMainBinding
import com.amadydev.alkemymoviechallenge.domain.usecases.GetPopularMoviesUseCase
import com.amadydev.alkemymoviechallenge.presentation.viewmodel.MovieViewModel
import com.amadydev.alkemymoviechallenge.presentation.viewmodel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MovieViewModel by viewModels()

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
        viewModel.getMovies()
    }

    private fun onClickEvents() {
        TODO("Not yet implemented")
    }

    private fun initRecycler() {
        binding.rvMovies.
    }

    private fun viewModelObservers() {
        TODO("Not yet implemented")
    }


}