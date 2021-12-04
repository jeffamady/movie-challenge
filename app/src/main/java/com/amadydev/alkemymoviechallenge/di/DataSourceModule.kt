package com.amadydev.alkemymoviechallenge.di

import com.amadydev.alkemymoviechallenge.data.TMDB
import com.amadydev.alkemymoviechallenge.data.remote.datasource.IDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(TMDB.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideIDataSource(retrofit: Retrofit): IDataSource {
        return retrofit.create(IDataSource::class.java)
    }
}