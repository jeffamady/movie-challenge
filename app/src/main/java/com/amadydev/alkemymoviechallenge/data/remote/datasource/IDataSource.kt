package com.amadydev.alkemymoviechallenge.data.remote.datasource

import com.amadydev.alkemymoviechallenge.data.remote.entities.MovieResponse
import com.amadydev.alkemymoviechallenge.data.remote.entities.RateMovieRequest
import com.amadydev.alkemymoviechallenge.data.remote.entities.RateMovieResponse
import com.amadydev.alkemymoviechallenge.data.remote.entities.SessionResponse
import retrofit2.Response
import retrofit2.http.*

interface IDataSource {
    @GET("movie/popular?")
    suspend fun fetchPopularMovies(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): Response<MovieResponse>

    @GET("search/movie?")
    suspend fun fetchMovieByName(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page: Int,
    ): Response<MovieResponse>

    @GET("authentication/guest_session/new?")
    suspend fun fetchGuestSessionId(
        @Query("api_key") api_key: String,
    ): Response<SessionResponse>

    @POST("movie/{movie_id}/rating?")
    @Headers("Accept:application/json")
    suspend fun rateMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("guest_session_id") guest_session_id: String,
        @Body rateMovieRequest: RateMovieRequest,
    ): Response<RateMovieResponse>
}