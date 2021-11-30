package com.amadydev.alkemymoviechallenge.data.remote.datasource

import com.amadydev.alkemymoviechallenge.data.DB
import com.amadydev.alkemymoviechallenge.data.remote.dto.MoviesDTO
import com.amadydev.alkemymoviechallenge.domain.ObjectResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataSource @Inject constructor(private val dataSource: IDataSource) {
    suspend fun getPopularMovies(): ObjectResult<MoviesDTO> {
        return try {
            withContext(Dispatchers.IO){
                val result = dataSource.fetchPopularMovies(DB.api_key)
                ObjectResult.Success(result.body()!!)
            }
        } catch (ex: Exception){
            ObjectResult.Failure(ex)
        }
    }

    suspend fun getMovieByName(query: String): ObjectResult<MoviesDTO> {
        return try {
            withContext(Dispatchers.IO){
                val result = dataSource.fetchMovieByName(DB.api_key,query)
                ObjectResult.Success(result.body()!!)
            }
        } catch (ex: Exception) {
            ObjectResult.Failure(ex)
        }
    }

}