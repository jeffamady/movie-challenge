package com.amadydev.alkemymoviechallenge.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.amadydev.alkemymoviechallenge.data.remote.repository.Repository
import com.amadydev.alkemymoviechallenge.domain.entities.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPopularMoviesUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(): LiveData<PagingData<Movie>> =
        repository.getPopularMovies()
}