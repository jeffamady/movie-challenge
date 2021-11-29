package com.amadydev.alkemymoviechallenge.data.remote.datasource

import com.amadydev.alkemymoviechallenge.data.remote.dto.MoviesDTO
import com.amadydev.alkemymoviechallenge.domain.ObjectResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IDataSource {
    @GET("movie/popular")
    suspend fun fetchPopularMovies(@Query("api_key") api_key: String): Response<MoviesDTO>

    @GET("search/movie?")
    suspend fun fetchMovieByName(@Query("api_key") api_key: String, @Query("movie_name") movie_name: String): Response<MoviesDTO>
}