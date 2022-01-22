package com.umc.clear.utils

import android.graphics.Rect
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
        val pos = parent.getChildViewHolder(view).adapterPosition
        val count = state.itemCount
        view.setPadding(10, 10, 10, 10)
        if (count == pos - 1) {
            outRect.right = 0
        }
        else {
            outRect.right = space
        }
    }
}