package com.amadydev.alkemymoviechallenge.data

import com.amadydev.alkemymoviechallenge.data.remote.datasource.IDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataSourceModule {
    fun profideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(DB.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun profideIDataSouce(retrofit: Retrofit): IDataSource {
        return retrofit.create(IDataSource::class.java)
    }
}