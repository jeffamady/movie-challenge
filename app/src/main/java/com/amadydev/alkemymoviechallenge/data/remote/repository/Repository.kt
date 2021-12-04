package com.amadydev.alkemymoviechallenge.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.amadydev.alkemymoviechallenge.data.remote.datasource.IDataSource
import com.amadydev.alkemymoviechallenge.data.remote.datasource.MoviePagingSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: IDataSource
) {




    fun getMovieByName(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(api, query) }
        ).liveData


//    fun getPopularMovies() =
//        Pager(
//            config = PagingConfig(
//                pageSize = 20,
//                maxSize = 100,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = { MoviePagingSource(api) }
//        ).liveData

    fun getPopularMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(api) }
        ).liveData


}