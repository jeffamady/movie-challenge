package com.amadydev.alkemymoviechallenge.domain.usecases

import com.amadydev.alkemymoviechallenge.data.remote.entities.RateMovieRequest
import com.amadydev.alkemymoviechallenge.data.remote.repository.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RateMovie @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(
        movieId: Int,
        guestSessionId: String,
        rateMovieRequest: RateMovieRequest
    ): String =
        repository.rateMovie(movieId, guestSessionId, rateMovieRequest)
}