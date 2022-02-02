package com.umc.clear.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.databinding.ItemHomeCalendarFrameBinding
import com.umc.clear.databinding.ItemHomeHeaderBinding
import com.umc.clear.databinding.ItemHomeRankBinding

class HomeRVAdapter(val context: Context, val dataList: ArrayList<String>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return dataList[0].toInt()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            1-> {
                val binding = ItemHomeHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                headerHolder(binding)
            }
            2-> {
                val binding = ItemHomeCalendarFrameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                calFrameHolder(binding)
            }
            else-> {
                val binding = ItemHomeRankBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                rankHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(dataList[0].toInt()) {
            1-> {
                (holder as HomeRVAdapter.headerHolder).init()
            }
            2-> {
                (holder as HomeRVAdapter.calFrameHolder).init()
            }
            else-> {
                (holder as HomeRVAdapter.rankHolder).init()
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    inner class headerHolder(private val binding: ItemHomeHeaderBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun init() {
                binding.homeUserInfoTv.text = dataList[1]
            }
    }

    inner class calFrameHolder(private val binding: ItemHomeCalendarFrameBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun init() {
                //binding.homeCalVp.adapter
            }

    }

    inner class rankHolder(private val binding: ItemHomeRankBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun init() {
            }

    }
}