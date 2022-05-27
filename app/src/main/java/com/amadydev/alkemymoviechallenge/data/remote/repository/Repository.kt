package com.amadydev.alkemymoviechallenge.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.amadydev.alkemymoviechallenge.data.remote.datasource.IDataSource
import com.amadydev.alkemymoviechallenge.data.remote.datasource.PagingDataSource
import com.amadydev.alkemymoviechallenge.data.remote.datasource.RemoteDataSource
import com.amadydev.alkemymoviechallenge.data.remote.entities.RateMovieRequest
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: IDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    fun getMovieByName(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingDataSource(api, query) }
        ).liveData

    fun getPopularMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingDataSource(api) }
        ).liveData


    suspend fun getGuestSessionId(): String =
        remoteDataSource.getGuestSessionId()

    suspend fun rateMovie(movieId: Int, guestSessionId: String, rateMovieRequest: RateMovieRequest): String =
        remoteDataSource.rateMovie(movieId, guestSessionId, rateMovieRequest)
}