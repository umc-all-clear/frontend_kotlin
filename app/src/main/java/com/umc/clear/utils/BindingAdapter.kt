package com.umc.clear.utils

import android.view.View
import androidx.core.view.doOnLayout
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("change_height")
    fun setCalHeight(view: View, isTrue: Boolean) {
        view.layoutParams = view.layoutParams.apply {
            view.doOnLayout {
                if (isTrue) {
                    this.height = PrefApp.glob.getHeight()
                }
            }
            //liveHeight.value = PrefApp.glob.getHeight().toString() + "dp"
        }
    }

    fun dpTopx(dp: Int, dpi: Float) : Int = (dp * dpi).toInt()
    fun pxTodp(px: Double, dpi: Float): Int = (px / dpi).toInt()
}