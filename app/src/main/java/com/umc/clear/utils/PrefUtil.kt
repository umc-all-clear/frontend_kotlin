package com.umc.clear.utils

import android.content.Context
import android.content.SharedPreferences

class PrefUtil(context: Context) {
    val prefName = "deviceInfo"
    val pref: SharedPreferences = context.getSharedPreferences(prefName, 0)

    fun getString(key: String): String {
        return pref.getString(key, "").toString()
    }

    fun putString(key: String, string: String) {
        pref.edit().putString(key, string).apply()
    }
}