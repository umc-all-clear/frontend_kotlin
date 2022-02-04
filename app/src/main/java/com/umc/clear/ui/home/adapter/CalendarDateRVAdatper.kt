package com.umc.clear.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.databinding.ItemHomeCalendarDateBinding
import com.umc.clear.utils.PrefApp

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
    ): viewHolder {
        val binding: ItemHomeCalendarDateBinding = ItemHomeCalendarDateBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setting(position)
    }

    override fun getItemCount(): Int {
        return 7
    }

    inner class viewHolder(val binding: ItemHomeCalendarDateBinding): RecyclerView.ViewHolder(binding.root) {
        fun setting(position: Int) {
            binding.itemCalDateTv.text = (data.second + position).toString()

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