package com.amadydev.alkemymoviechallenge.di

import com.amadydev.alkemymoviechallenge.data.TMDb
import com.amadydev.alkemymoviechallenge.data.remote.datasource.IDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        return Retrofit.Builder()
            .baseUrl(TMDb.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideIDataSource(retrofit: Retrofit): IDataSource {
        return retrofit.create(IDataSource::class.java)
    }
}