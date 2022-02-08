package com.umc.clear.utils

import android.util.Log
import android.view.View
import androidx.core.view.doOnLayout
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("change_all_height")
    fun setAllHeight(view: View, isTrue: Boolean) {
        if (isTrue) {
            view.layoutParams = view.layoutParams.apply {
                view.doOnLayout {
                    this.height = PrefApp.glob.getAllHeight()
                    Log.d("height", this.height.toString())
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("change_cal_height")
    fun setCalHeight(view: View, isTrue: Boolean) {
        if (isTrue) {
            view.layoutParams = view.layoutParams.apply {
                view.doOnLayout {
                    this.height = PrefApp.glob.getCalHeight()
                    Log.d("height", this.height.toString())
                }
            }
        }
    }



    fun dpTopx(dp: Int, dpi: Float) : Int = (dp * dpi).toInt()
    fun pxTodp(px: Double, dpi: Float): Int = (px / dpi).toInt()
}