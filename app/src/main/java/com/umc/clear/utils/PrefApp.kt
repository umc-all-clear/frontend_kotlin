package com.umc.clear.utils

import android.app.Application

class PrefApp : Application() {
    companion object {
        lateinit var pref: PrefUtil
    }

    override fun onCreate() {
        super.onCreate()
        pref = PrefUtil(applicationContext)
    }
}