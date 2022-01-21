package com.umc.clear.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.databinding.ItemHomeCalendarDateBinding

class CalendarDateRVAdatper(val data: ArrayList<Int>, val width: Int): RecyclerView.Adapter<CalendarDateRVAdatper.viewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarDateRVAdatper.viewHolder {
        val binding: ItemHomeCalendarDateBinding = ItemHomeCalendarDateBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarDateRVAdatper.viewHolder, position: Int) {
        holder.setting()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class viewHolder(val binding: ItemHomeCalendarDateBinding): RecyclerView.ViewHolder(binding.root) {
        fun setting() {
            binding.itemCalDateTv.text = data[0].toString()
            val par = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            par.setMargins(-10, -10, -10, -10)
            binding.itemCalDateCv.layoutParams = par
            val default = (width - (67* 2)) / 7
            if (default < 65) {
            }
        }
    }
}