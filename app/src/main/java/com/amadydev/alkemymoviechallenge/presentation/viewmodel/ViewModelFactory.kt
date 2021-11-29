package com.amadydev.alkemymoviechallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amadydev.alkemymoviechallenge.domain.usecases.GetPopularMoviesUseCase

class ViewModelFactory(private val getPopularMoviesUseCase: GetPopularMoviesUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MovieViewModel(getPopularMoviesUseCase) as T
}