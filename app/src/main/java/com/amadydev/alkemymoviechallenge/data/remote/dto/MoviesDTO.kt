package com.amadydev.alkemymoviechallenge.data.remote.dto

import com.amadydev.alkemymoviechallenge.domain.entities.Movies
import com.google.gson.annotations.SerializedName

data class MoviesDTO (
        @SerializedName("page") val page:Int,
        @SerializedName("results") val movies:List<Movies>,
        @SerializedName("total_pages") val totalPages:Int,
        @SerializedName("total_results") val totalResult:Int
)