package com.amadydev.alkemymoviechallenge.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amadydev.alkemymoviechallenge.AlkemyMovieChallengeApp.Companion.prefs
import com.amadydev.alkemymoviechallenge.domain.usecases.GetGuestSessionId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getGuestSessionId: GetGuestSessionId
) :
    ViewModel() {
    fun checkGuestSessionId() {
        if (prefs.getGuestSessionId().isEmpty()) {
            viewModelScope.launch {
                val sessionId = getGuestSessionId()//.also(::println)
                prefs.saveGuestSessionId(sessionId)
            }
        }
    }
}