package com.umc.clear.ui.admission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.databinding.ItemAdmissionAdmisPageBinding
import com.umc.clear.databinding.ItemAdmissionWaitingPageBinding

class AdmissionContentVPAdapter(val data: ArrayList<Int>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return data[position]
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when(viewType) {
            1-> {
                val binding = ItemAdmissionAdmisPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AdmissionHolder(binding)
            }
            else-> {
                val binding = ItemAdmissionWaitingPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                WaitingHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(data[position]) {
            1-> {
                (holder as AdmissionContentVPAdapter.AdmissionHolder).init()
            }
            else-> {
                (holder as AdmissionContentVPAdapter.WaitingHolder).init()
            }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    inner class AdmissionHolder(val binding: ItemAdmissionAdmisPageBinding): RecyclerView.ViewHolder(binding.root) {
        fun init() {

        }
    }

    inner class WaitingHolder(val binding: ItemAdmissionWaitingPageBinding): RecyclerView.ViewHolder(binding.root) {
        fun init() {
            val a = ArrayList<Int>()
            a.add(1)
            a.add(2)
            a.add(1)
            a.add(2)
            a.add(1)
            a.add(2)
            a.add(1)
            a.add(2)
            a.add(1)
            a.add(2)
            a.add(1)
            a.add(1)
            a.add(1)
            binding.itemAdmisWaitingRv.adapter = AdmissionWaitingRVAdapter(a)
        }
    }
}