package com.amadydev.alkemymoviechallenge.data.remote.datasource

import com.amadydev.alkemymoviechallenge.data.TMDb
import com.amadydev.alkemymoviechallenge.data.remote.entities.RateMovieRequest
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: IDataSource) {
//    lateinit var res: String

    suspend fun getGuestSessionId(): String {
        return try {
            val response = api.fetchGuestSessionId(TMDb.api_key)
            response.body()?.guestSessionId!!
        } catch (ex: IOException) {
            ex.message!!
        } catch (ex: HttpException) {
            ex.message!!
        }
    }

    suspend fun rateMovie(movieId: Int, guestSessionId: String, rateMovieRequest: RateMovieRequest): String {
        val response = api.rateMovie(movieId, TMDb.api_key, guestSessionId, rateMovieRequest)

        return if (response.isSuccessful) response.body()?.statusMessage!!
        else "Oop! An error occurred"
    }
}