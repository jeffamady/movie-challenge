package com.amadydev.alkemymoviechallenge.domain.usecases

import com.amadydev.alkemymoviechallenge.data.remote.repository.Repository
import com.amadydev.alkemymoviechallenge.domain.ObjectResult
import com.amadydev.alkemymoviechallenge.domain.entities.Movies
import com.amadydev.alkemymoviechallenge.domain.repository.ILocalRepository

class GetPopularMoviesUseCase(private val repository: Repository) {
    suspend operator fun invoke(): ObjectResult<List<Movies>?> = repository.getMovies()
}