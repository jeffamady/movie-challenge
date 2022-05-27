package com.amadydev.alkemymoviechallenge.ui.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amadydev.alkemymoviechallenge.data.remote.entities.RateMovieRequest
import com.amadydev.alkemymoviechallenge.domain.usecases.RateMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val rateMovie: RateMovie,
) :
    ViewModel() {
    private val mResponse = MutableLiveData<String>()
    val response = mResponse

    fun rateTheMovie(
        movieId: Int,
        guestSessionId: String,
        rateMovieRequest: RateMovieRequest
    ) {
        Log.d("Guest id", guestSessionId)
        viewModelScope.launch {
            mResponse.value = rateMovie(movieId, guestSessionId, rateMovieRequest).also(::println)
            Log.d("Details view", "$mResponse")
        }
    }

}