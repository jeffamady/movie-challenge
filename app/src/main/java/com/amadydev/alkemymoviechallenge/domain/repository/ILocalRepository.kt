package com.amadydev.alkemymoviechallenge.domain.repository

import com.amadydev.alkemymoviechallenge.data.remote.dto.MoviesDTO
import com.amadydev.alkemymoviechallenge.domain.ObjectResult
import com.amadydev.alkemymoviechallenge.domain.entities.Movies

interface ILocalRepository {
    suspend fun getMovies(): ObjectResult<List<Movies>?>
    suspend fun getMovie(query: String): ObjectResult<List<Movies>?>
}