package com.amadydev.alkemymoviechallenge.data.remote.datasource

import android.content.Context
import com.amadydev.alkemymoviechallenge.data.DB
import com.amadydev.alkemymoviechallenge.data.remote.dto.MoviesDTO
import com.amadydev.alkemymoviechallenge.domain.ObjectResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataSource(private val dataSource: IDataSource) {
    suspend fun getPopularMovies(): ObjectResult<MoviesDTO?> {
        return withContext(Dispatchers.IO) {
            try {

                ObjectResult.Success(response.body())
            } catch (ex: Exception){
                ObjectResult.Failure(ex)
            }
        }
        return try {
            withContext(Dispatchers.IO){
                val response = dataSource.fetchPopularMovies(DB.api_key)
                ObjectResult.Success(response.body())
            }
        } catch (ex: Exception){
            ObjectResult.Failure(ex)
        }
    }

    suspend fun getMovieByName(query: String): ObjectResult<MoviesDTO?> {
        return withContext(Dispatchers.IO) {
            when (val response = dataSource.fetchMovieByName(DB.api_key,query)) {
                is ObjectResult.Success -> ObjectResult.Success(response.data.body())
                is ObjectResult.Failure -> ObjectResult.Failure(response.exception)
            }
        }
    }

}