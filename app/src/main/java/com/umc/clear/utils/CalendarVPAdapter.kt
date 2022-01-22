package com.umc.clear.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.R
import com.umc.clear.databinding.ItemHomeCalendarBinding
import com.umc.clear.ui.MainActivity

class CalendarVPAdapter(val dataList: ArrayList<Int>, val context: Context, val width: Int): RecyclerView.Adapter<CalendarVPAdapter.viewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarVPAdapter.viewHolder {
        val binding: ItemHomeCalendarBinding = ItemHomeCalendarBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.itemView
        holder.setRVAdapter()
        holder.setFL()
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    inner class viewHolder(val binding:ItemHomeCalendarBinding): RecyclerView.ViewHolder(binding.root) {
        fun setRVAdapter() {

            binding.itemCalDate1Rv.overScrollMode = View.OVER_SCROLL_NEVER
            val manager = CustomLinearLayout(context)
            binding.itemCalDate1Rv.layoutManager = manager
            val default = (width - (67 * 2)) / 7
            if (default < 65) {
                binding.itemCalDate1Rv.addItemDecoration(CustomItem(default))
            }
            else {
                binding.itemCalDate1Rv.addItemDecoration(CustomItem(65))

            }
            binding.itemCalDate1Rv.adapter = CalendarDateRVAdatper(dataList, width)

            val arr = ArrayList<Int>()
            arr.add(2)
            arr.add(3)
            arr.add(4)
            arr.add(5)
            arr.add(3)
            arr.add(4)
            arr.add(5)
            binding.itemCalDate2Rv.adapter = CalendarDateRVAdatper(arr, width)



        }

        fun setFL() {
            val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes1Fl, true)
            inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes2Fl, true)
            inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes3Fl, true)
            inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes4Fl, true)
            inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes5Fl, true)
            inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes6Fl, true)

        }
    }
}