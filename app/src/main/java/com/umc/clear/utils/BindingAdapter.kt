package com.umc.clear.utils

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("change_all_height")
    fun setAllHeight(view: View, isTrue: Boolean) {
        if (isTrue) {
            view.layoutParams = view.layoutParams.apply {
                this.height = PrefApp.glob.getAllHeight()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("change_cal_height")
    fun setCalHeight(view: View, isTrue: Boolean) {
        if (isTrue) {
            view.layoutParams = view.layoutParams.apply {
                this.height = PrefApp.glob.getCalHeight()
            }
        }
    }



    fun dpTopx(dp: Int, dpi: Float) : Int = (dp * dpi).toInt()
    fun pxTodp(px: Double, dpi: Float): Int = (px / dpi).toInt()
}