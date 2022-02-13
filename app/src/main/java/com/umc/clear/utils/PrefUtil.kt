package com.umc.clear.utils

import android.content.Context
import android.content.SharedPreferences

class PrefUtil(val context: Context) {
    var prefName = "deviceInfo"
    var pref: SharedPreferences = context.getSharedPreferences(prefName, 0)

    fun setPrefname(name: String) {
        pref = context.getSharedPreferences(name, 0)
    }
    fun getString(key: String): String {
        return pref.getString(key, "").toString()
    }

    fun putString(key: String, string: String) {
        pref.edit().putString(key, string).apply()
    }
}