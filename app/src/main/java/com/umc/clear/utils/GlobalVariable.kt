package com.umc.clear.utils

import android.app.Application
import android.graphics.Typeface
import com.umc.clear.data.entities.dataResult
import com.umc.clear.databinding.ItemHomeCalendarDateBinding

class GlobalVariable() : Application() {
    private var calHeight = 0
    private var elseHeight = 0
    private var calPage = 1000

    private var rvHeight = 0
    private var flEmptyHeight = 0
    private var flContentHeight = 0

    private var isDelete = true

    private var waitingDataArr0 = ArrayList<dataResult>()
    private var waitingDataArr1 = ArrayList<dataResult>()

    var selectedDate: ItemHomeCalendarDateBinding? = null
//
//    private var firstContentFrameCall = true
//    private var firstEmptyFrameCall = true
//
//    fun setFirstCall(type: Int) {
//        if (type == 1) {
//            firstEmptyFrameCall = false
//        }
//        else (type == 2)
//    }
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
            h * rvHeight * 3 + flContentHeight
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

    fun getDel():Boolean {
        return isDelete
    }

    fun setDel(data: Boolean) {
        isDelete = data
    }

    fun setDate(binding: ItemHomeCalendarDateBinding) {
        if (selectedDate != null) {
            selectedDate!!.itemCalDateTv.typeface = Typeface.DEFAULT
        }
        selectedDate = binding
        selectedDate!!.itemCalDateTv.typeface = Typeface.DEFAULT_BOLD
    }

    fun closeDate() {
        if (selectedDate != null) {
            selectedDate!!.itemCalDateTv.typeface = Typeface.DEFAULT
        }
    }

    fun setWaitingDataArr(dataArr: ArrayList<dataResult>, order: Int) {
        dataArr.reverse()
        if (order == 1) {
            this.waitingDataArr1 = dataArr
        }
        else {
            this.waitingDataArr0 = dataArr
        }
    }

    fun getWaitingDataArr(): ArrayList<dataResult> {
        var joinAll = ArrayList<dataResult>()
        joinAll.addAll(this.waitingDataArr0)
        joinAll.addAll(this.waitingDataArr1)
        return joinAll
    }

    fun dpTopx(dp: Int, dpi: Float) : Int = (dp * dpi).toInt()
    fun pxTodp(px: Double, dpi: Float): Int = (px / dpi).toInt()
}