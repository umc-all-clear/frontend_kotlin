package com.umc.clear.ui.admission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.databinding.ItemAdmissionWaitingPageContentBinding
import com.umc.clear.databinding.ItemAdmissionWaitingPageDateBinding

class AdmissionWaitingRVAdapter(val data: ArrayList<Int>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return data[position]
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when(viewType) {
            1-> {
                val binding = ItemAdmissionWaitingPageContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ContentHolder(binding)
            }
            else-> {
                val binding = ItemAdmissionWaitingPageDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DateHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(data[position]) {
            1-> {
                (holder as AdmissionWaitingRVAdapter.ContentHolder).init(position)
            }
            else-> {
                (holder as AdmissionWaitingRVAdapter.DateHolder).init(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ContentHolder(val binding: ItemAdmissionWaitingPageContentBinding): RecyclerView.ViewHolder(binding.root) {
        fun init(pos: Int) {
        }
    }

    inner class DateHolder(val binding: ItemAdmissionWaitingPageDateBinding): RecyclerView.ViewHolder(binding.root) {
        fun init(pos: Int) {

        }
    }
}