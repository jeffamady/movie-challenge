package com.amadydev.alkemymoviechallenge.domain.usecases

import com.amadydev.alkemymoviechallenge.domain.ObjectResult
import com.amadydev.alkemymoviechallenge.domain.entities.Movies
import com.amadydev.alkemymoviechallenge.domain.repository.ILocalRepository

class GetMovieByNameUseCase(private val repository: ILocalRepository) {
    suspend operator fun invoke(query: String): ObjectResult<List<Movies>?> = repository.getMovie(query)
}