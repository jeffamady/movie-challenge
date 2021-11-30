package com.amadydev.alkemymoviechallenge.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amadydev.alkemymoviechallenge.domain.ObjectResult
import com.amadydev.alkemymoviechallenge.domain.entities.Movies
import com.amadydev.alkemymoviechallenge.domain.usecases.GetMovieByNameUseCase
import com.amadydev.alkemymoviechallenge.domain.usecases.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getMovieByNameUseCase: GetMovieByNameUseCase,
) :
    ViewModel() {

    private val mList = MutableLiveData<List<Movies>>()
    val list: LiveData<List<Movies>> = mList

    private val mOnError = MutableLiveData<String>()
    val onError: LiveData<String> = mOnError

    private val mIsLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = mIsLoading


    fun getMovies(context: Context) {
        viewModelScope.launch {
            mIsLoading.postValue(true)
            when (val result = getPopularMoviesUseCase()) {
                is ObjectResult.Success -> {
                    if (result.data.isNotEmpty()) {
                        this@MovieViewModel.mList.value = result.data
                        mIsLoading.postValue(false)
                    } else {
                        Toast.makeText(
                            context,
                            "It seems we don't have this movie or something went wrong. Please try again!",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
                is ObjectResult.Failure -> this@MovieViewModel.mOnError.value =
                    result.exception.message
            }
        }
    }

    fun getMovieByName(query: String, context: Context) {
        viewModelScope.launch {
            mIsLoading.postValue(true)
            when (val result = getMovieByNameUseCase(query)) {
                is ObjectResult.Success -> {
                    if (result.data.isNotEmpty()) {
                        this@MovieViewModel.mList.value = result.data
                        mIsLoading.postValue(false)
                    } else {
                        Toast.makeText(
                            context,
                            "It seems we don't have this movie or something went wrong. Please try again!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is ObjectResult.Failure -> this@MovieViewModel.mOnError.value =
                    result.exception.message
            }
        }

    }
}