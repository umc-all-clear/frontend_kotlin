package com.umc.clear.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.databinding.ItemHomeCalendarDesCommBinding

class DescriptionCommRVAdapter(val data: ArrayList<String>): RecyclerView.Adapter<DescriptionCommRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DescriptionCommRVAdapter.ViewHolder {
        val binding = ItemHomeCalendarDesCommBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DescriptionCommRVAdapter.ViewHolder, position: Int) {
        holder.init(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val binding: ItemHomeCalendarDesCommBinding): RecyclerView.ViewHolder(binding.root) {
        fun init(pos: Int) {
            binding.itemCommTv.text = data[pos]
        }

    }
}