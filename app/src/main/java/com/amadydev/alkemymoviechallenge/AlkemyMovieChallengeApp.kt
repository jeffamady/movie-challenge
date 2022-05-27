package com.amadydev.alkemymoviechallenge

import android.app.Application
import com.amadydev.alkemymoviechallenge.data.local.prefs.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlkemyMovieChallengeApp: Application(){
    companion object{
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}