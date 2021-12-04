package com.amadydev.alkemymoviechallenge.ui.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.amadydev.alkemymoviechallenge.domain.usecases.GetMovieByNameUseCase
import com.amadydev.alkemymoviechallenge.domain.usecases.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getMovieByNameUseCase: GetMovieByNameUseCase,
    state: SavedStateHandle,
) :
    ViewModel() {


//        private val currentQuery = MutableLiveData<String>()
    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)



    var popularMovies = getPopularMoviesUseCase().cachedIn(viewModelScope)

    var movies = currentQuery.switchMap {
        getMovieByNameUseCase(it).cachedIn(viewModelScope)
    }



    fun searchMovie(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = "ve"
    }


}

