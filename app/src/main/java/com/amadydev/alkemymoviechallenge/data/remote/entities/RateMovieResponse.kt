package com.amadydev.alkemymoviechallenge.data.remote.entities

import com.google.gson.annotations.SerializedName

data class RateMovieResponse(
    @SerializedName("success") val success:Boolean,
    @SerializedName("status_code") val statusCode:Int,
    @SerializedName("status_message") val statusMessage:String
)
