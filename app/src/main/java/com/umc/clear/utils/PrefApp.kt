package com.umc.clear.utils

import android.app.Application

class PrefApp : Application() {
    companion object {
        lateinit var pref: PrefUtil
        lateinit var glob: GlobalVariable
    }

    override fun onCreate() {
        super.onCreate()
        pref = PrefUtil(applicationContext)
        glob = GlobalVariable(applicationContext)
    }
}