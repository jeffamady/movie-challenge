package com.amadydev.alkemymoviechallenge.data.remote.repository

import com.amadydev.alkemymoviechallenge.data.remote.datasource.DataSource
import com.amadydev.alkemymoviechallenge.domain.ObjectResult
import com.amadydev.alkemymoviechallenge.domain.entities.Movies
import javax.inject.Inject

class Repository @Inject constructor(private val dataSource: DataSource) {
    suspend fun getMovies(): ObjectResult<List<Movies>> {
        return when (val result = dataSource.getPopularMovies()) {
            is ObjectResult.Success -> {
                ObjectResult.Success(result.data.movies)
            }
            is ObjectResult.Failure -> ObjectResult.Failure(result.exception)
        }
    }

    suspend fun getMovieByName(query: String): ObjectResult<List<Movies>> {



        return when(val result = dataSource.getMovieByName(query)) {
            is ObjectResult.Success -> {
                ObjectResult.Success(result.data.movies)
            }
            is ObjectResult.Failure -> ObjectResult.Failure(result.exception)
        }
    }

}