package com.umc.clear.utils

import android.app.Application
import android.content.Context

class GlobalVariable() : Application() {
    private var calHeight = 0
    private var elseHeight = 0
    private var calPage = 1000

    private var rvHeight = 0
    private var flHeight = 0

    fun getAllHeight(): Int {
        return calHeight + elseHeight
    }

    fun getCalHeight(): Int {
        return calHeight
    }

    fun setCalHeight(h: Int) {
        this.calHeight = h * rvHeight
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

    fun getRvHeight(): Int {
        return this.rvHeight
    }

    fun setRvHeight(h: Int) {
        this.rvHeight = h
    }
}