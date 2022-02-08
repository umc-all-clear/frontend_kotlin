package com.umc.clear.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.databinding.ItemHomeCalendarDesImgBinding

class DescriptionImgRVAdapter(val data: ArrayList<Int>): RecyclerView.Adapter<DescriptionImgRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DescriptionImgRVAdapter.ViewHolder {
        val binding = ItemHomeCalendarDesImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DescriptionImgRVAdapter.ViewHolder, position: Int) {
        holder.init(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val binding: ItemHomeCalendarDesImgBinding): RecyclerView.ViewHolder(binding.root) {
        fun init(pos: Int) {
            binding.itemImgIv.setImageResource(data[pos])
        }
    }
}