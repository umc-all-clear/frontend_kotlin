package com.umc.clear.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.databinding.ItemHomeCalendarDateBinding

class CalendarDateRVAdatper(val data: Pair<Int, Int>): RecyclerView.Adapter<CalendarDateRVAdatper.viewHolder>() {

    interface onclickListener {
        fun onClick()
    }

    lateinit var clickListener: onclickListener

    fun setListener(listen: onclickListener) {
        clickListener = listen
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarDateRVAdatper.viewHolder {
        val binding: ItemHomeCalendarDateBinding = ItemHomeCalendarDateBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarDateRVAdatper.viewHolder, position: Int) {
        holder.setting(position)
    }

    override fun getItemCount(): Int {
        return if (data.first == 0) {
            7
        }
        else {
            data.first
        }
    }

    inner class viewHolder(val binding: ItemHomeCalendarDateBinding): RecyclerView.ViewHolder(binding.root) {
        fun setting(position: Int) {
            for (i in data.second until data.second+itemCount) {
                binding.itemCalDateTv.text = i.toString()
            }

            var pos = IntArray(2)
            binding.itemCalDateTv.doOnLayout {
                if (position == 0) {
                    binding.itemCalDateIv.getLocationInWindow(pos)
                    PrefApp.pref.putString("calxPos", pos[0].toString())
                }
            }
        }
    }
}