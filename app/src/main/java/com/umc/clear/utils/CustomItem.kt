package com.umc.clear.utils

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomItem(var space: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
//        val pos = parent.getChildViewHolder(view).adapterPosition
//        val count = state.itemCount
//        val dpi = PrefApp.pref.getString("dpi").toFloat()
//        if (space < dpTopx(65, dpi)) {
//            view.setPadding((space - dpTopx(20, dpi))/2 -1, dpTopx(10, dpi), (space - dpTopx(20, dpi))/2, dpTopx(10, dpi))
//        }
//        else {
//            view.setPadding(dpTopx(22, dpi), dpTopx(10, dpi), dpTopx(22, dpi), dpTopx(10, dpi))
//
//            Log.d("spmore", pxTodp(space.toDouble(), dpi).toString())
//            if (count == pos - 1) {
//                outRect.right = 0
//            }
//            else {
//                outRect.right = space - dpTopx(64, dpi) + (space - dpTopx(64, dpi))/6
//            }//margin setting
//
//        }
    }



    fun dpTopx(dp: Int, dpi: Float) : Int = (dp * dpi).toInt()
    fun pxTodp(px: Double, dpi: Float): Int = (px / dpi).toInt()

}