package com.umc.clear.ui.home.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.databinding.ItemHomeCalendarDateBinding
import com.umc.clear.databinding.ItemHomeCalendarFrameBinding
import com.umc.clear.utils.PrefApp

class CalendarDateRVAdapter(val data: ArrayList<Int>, val mainCont: Context, val parBinding: ItemHomeCalendarFrameBinding): RecyclerView.Adapter<CalendarDateRVAdapter.ViewHolder>() {

    interface onclickListener {
        fun onClick(date: Int)
    }

    lateinit var clickListener: onclickListener

    fun setListener(listen: onclickListener) {
        clickListener = listen
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemHomeCalendarDateBinding = ItemHomeCalendarDateBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setting(position)
        holder.itemView.setOnClickListener {
            PrefApp.glob.setDate(holder.binding)
            if (data[0] == 0) {
            if (data[1] + position <= data[3]) {
                clickListener.onClick(data[1] + position)
            }
        }
        else {
            if (position >= 7 - data[0]) {
                clickListener.onClick(position - 6 + data[0])
            }
        }
        }
    }

    override fun getItemCount(): Int {
        return 7
    }

    inner class ViewHolder(val binding: ItemHomeCalendarDateBinding): RecyclerView.ViewHolder(binding.root) {
        fun setting(position: Int) {

            if (data[0] == 0) {
                if (data[1] + position <= data[3]) {
                    binding.itemCalDateTv.text = (data[1] + position).toString()
                }
                else {
                    binding.itemCalDateTv.text = ""
                    binding.itemCalDateIv.visibility = View.GONE
                }
            }
            else {
                setGoneFirst(position)
                if (position >= 7 - data[0]) {
                    binding.itemCalDateTv.text = (position - 6 + data[0]).toString()
                }
            }
            var pos = IntArray(2)

            parBinding.homeCalMonTv.post {
                while (PrefApp.glob.getRvHeight() == 0) {
                }
                binding.itemCalDateTv.width = PrefApp.glob.getRvHeight()
                binding.itemCalDateTv.height = PrefApp.glob.getRvHeight()
            }
        }

        private fun setGoneFirst(position: Int) {
            when (data[0]) {
                1 -> {
                    if (position < 6) {
                        binding.itemCalDateTv.text = ""
                        binding.itemCalDateIv.visibility = View.GONE
                    }
                }

                2 -> {
                    if (position < 5) {
                        binding.itemCalDateTv.text = ""
                        binding.itemCalDateIv.visibility = View.GONE
                    }
                }
                3 -> {
                    if (position < 4) {
                        binding.itemCalDateTv.text = ""
                        binding.itemCalDateIv.visibility = View.GONE
                    }
                }
                4 -> {
                    if (position < 3) {
                        binding.itemCalDateTv.text = ""
                        binding.itemCalDateIv.visibility = View.GONE
                    }
                }
                5 -> {
                    if (position < 2) {
                        binding.itemCalDateTv.text = ""
                        binding.itemCalDateIv.visibility = View.GONE
                    }
                }
                6 -> {
                    if (position < 1) {
                        binding.itemCalDateTv.text = ""
                        binding.itemCalDateIv.visibility = View.GONE
                    }
                }
            }
        }
    }
}