package com.amadydev.alkemymoviechallenge.data.remote.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SessionResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("guest_session_id") val guestSessionId: String,
) : Parcelable