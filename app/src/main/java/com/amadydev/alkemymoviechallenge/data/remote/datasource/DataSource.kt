package com.amadydev.alkemymoviechallenge.data.remote.datasource

import com.amadydev.alkemymoviechallenge.data.TMDB
import com.amadydev.alkemymoviechallenge.data.remote.dto.MoviesDTO
import com.amadydev.alkemymoviechallenge.domain.ObjectResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataSource @Inject constructor(private val dataSource: IDataSource) {
    suspend fun getPopularMovies(page: Int): ObjectResult<MoviesDTO> {
        return try {
            withContext(Dispatchers.IO){
                val result = dataSource.fetchPopularMovies(TMDB.api_key, page)
                ObjectResult.Success(result.body()!!)
            }
        } catch (ex: Exception){
            ObjectResult.Failure(ex)
        }
    }

    suspend fun getMovieByName(query: String, page: Int): ObjectResult<MoviesDTO> {
        return try {
            withContext(Dispatchers.IO){
                val result = dataSource.fetchMovieByName(TMDB.api_key,query, page)
                ObjectResult.Success(result.body()!!)
            }
        } catch (ex: Exception) {
            ObjectResult.Failure(ex)
        }
    }

}