package com.amadydev.alkemymoviechallenge.data.local.prefs

import android.content.Context

class Prefs(val context: Context) {
    private val SHARED_NAME = "MyDB"
    private val SHARED_GUEST_SESSION_ID = "guest_session_id"

    private val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveGuestSessionId(sessionId: String) {
        storage.edit().putString(SHARED_GUEST_SESSION_ID, sessionId).apply()
    }

    fun getGuestSessionId(): String =
        storage.getString(SHARED_GUEST_SESSION_ID, "")!!

    fun wipe() {
        storage.edit().clear().apply()
    }
}