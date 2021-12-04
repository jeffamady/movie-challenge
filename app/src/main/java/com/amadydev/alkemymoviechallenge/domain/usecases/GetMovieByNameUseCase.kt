package com.amadydev.alkemymoviechallenge.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.amadydev.alkemymoviechallenge.data.remote.repository.Repository
import com.amadydev.alkemymoviechallenge.domain.entities.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieByNameUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(query: String): LiveData<PagingData<Movie>> =
        repository.getMovieByName(query)
}