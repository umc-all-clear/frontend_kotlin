package com.umc.clear.utils

import android.app.Application
import android.content.Context

class GlobalVariable() : Application() {
    private var calHeight = 0
    private var elseHeight = 0
    private var calPage = 1000

    private var rvHeight = 0
    private var flEmptyHeight = 0
    private var flContentHeight = 0

    fun getAllHeight(): Int {
        return calHeight + elseHeight + dpTopx(80, PrefApp.pref.getString("dpi").toFloat())
    }

    fun getCalHeight(): Int {
        return calHeight
    }

    fun setCalHeight(h: Int, frame: Int) {
        this.calHeight = if (frame == 0) {
            h * rvHeight
        }
        else if (frame == 1) {
            h*rvHeight + flEmptyHeight + dpTopx(30, PrefApp.pref.getString("dpi").toFloat())
        }
        else {
            h * rvHeight + flContentHeight + dpTopx(30, PrefApp.pref.getString("dpi").toFloat())
        }
    }

    fun setFlHeight(h: Int, type: Boolean) {
        if (type) {
            this.flContentHeight = h
        }
        else {
            this.flEmptyHeight = h
        }
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

    fun dpTopx(dp: Int, dpi: Float) : Int = (dp * dpi).toInt()
    fun pxTodp(px: Double, dpi: Float): Int = (px / dpi).toInt()
}