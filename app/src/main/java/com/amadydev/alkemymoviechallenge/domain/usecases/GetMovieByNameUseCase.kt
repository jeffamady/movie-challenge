package com.amadydev.alkemymoviechallenge.domain.usecases

import com.amadydev.alkemymoviechallenge.data.remote.repository.Repository
import com.amadydev.alkemymoviechallenge.domain.ObjectResult
import com.amadydev.alkemymoviechallenge.domain.entities.Movies
import javax.inject.Inject

class GetMovieByNameUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(query: String): ObjectResult<List<Movies>> = repository.getMovieByName(query)
}