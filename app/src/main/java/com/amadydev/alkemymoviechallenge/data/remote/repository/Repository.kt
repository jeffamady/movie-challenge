package com.amadydev.alkemymoviechallenge.data.remote.repository

import com.amadydev.alkemymoviechallenge.data.remote.datasource.DataSource
import com.amadydev.alkemymoviechallenge.data.remote.dto.MoviesDTO
import com.amadydev.alkemymoviechallenge.domain.ObjectResult

class Repository(private val dataSource: DataSource) {
    suspend fun getMovies(): ObjectResult<MoviesDTO?>{
        when (val response = dataSource.getPopularMovies()) {
            is ObjectResult.Success -> ObjectResult.Success(response.data.)
            is ObjectResult.Failure -> ObjectResult.Failure(response.exception)
        }
        return dataSource.getPopularMovies()
    }
    suspend fun getMovie(query: String): ObjectResult<MoviesDTO?>{
        return dataSource.getMovieByName(query)
    }
}