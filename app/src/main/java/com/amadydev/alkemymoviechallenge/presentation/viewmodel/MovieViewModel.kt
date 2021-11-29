package com.amadydev.alkemymoviechallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amadydev.alkemymoviechallenge.domain.ObjectResult
import com.amadydev.alkemymoviechallenge.domain.entities.Movies
import com.amadydev.alkemymoviechallenge.domain.usecases.GetPopularMoviesUseCase
import kotlinx.coroutines.launch

class MovieViewModel(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) : ViewModel() {
    private val mList = MutableLiveData<List<Movies>>()
    val list: LiveData<List<Movies>> = mList

    private val mOnError = MutableLiveData<String>()
    val onError: LiveData<String> = mOnError

    fun getMovies() {
        viewModelScope.launch {
            when (val result = getPopularMoviesUseCase()) {
                is ObjectResult.Success -> this@MovieViewModel.mList.value = result.data
                is ObjectResult.Failure -> this@MovieViewModel.mOnError.value = result.exception.message
            }
        }
    }
}