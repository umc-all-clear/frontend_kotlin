package com.umc.clear.utils

import android.app.Application
import android.content.Context

class GlobalVariable(context: Context) : Application() {
    private var calHeight = 0
    private var elseHeight = 0
    private var calPage = 1000

    fun getHeight(): Int {
        return calHeight + elseHeight
    }

    fun setCalHeight(h: Int) {
        this.calHeight = h
    }

    fun setElseHeight(h: Int) {
        this.elseHeight = h
    }

    fun getCalPage(): Int {
        return calPage
    }

    fun setCalPage(p: Int) {
        this.calPage = p
    }

}