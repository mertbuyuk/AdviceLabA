package com.mb.advlab

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AdvLabApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}