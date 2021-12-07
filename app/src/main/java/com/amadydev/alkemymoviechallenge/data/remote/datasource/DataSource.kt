package com.amadydev.alkemymoviechallenge.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amadydev.alkemymoviechallenge.data.TMDb
import com.amadydev.alkemymoviechallenge.domain.entities.Movie
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class DataSource @Inject constructor(
    private val api: IDataSource,
    private val query: String

) : PagingSource<Int, Movie>() {


    constructor(api: IDataSource) : this(api, query = "")


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: TMDb.starting_page_index


        return try {

            val response = if (query.isNotEmpty() && query != "") {
                api.fetchMovieByName(TMDb.api_key, query, page)
            } else {
                api.fetchPopularMovies(TMDb.api_key, page)
            }
            val movies = response.body()?.movies!!
//            response.body()?.page.also(::println)
            LoadResult.Page(
                data = movies,
                prevKey = if (page == TMDb.starting_page_index) null else page - 1,
                nextKey = if (response.body()?.movies?.isEmpty()!!) null else page + 1
            )
        } catch (ex: IOException) {
            LoadResult.Error(ex)
        } catch (ex: HttpException) {
            LoadResult.Error(ex)
        }
    }

}