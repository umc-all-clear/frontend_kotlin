package com.umc.clear.ui.admission.adapter

import android.Manifest
import android.app.Activity
import android.app.Instrumentation
import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Gallery
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.databinding.ItemAdmissionAdmisPageBinding
import com.umc.clear.databinding.ItemAdmissionWaitingPageBinding
import com.umc.clear.ui.MainActivity
import com.umc.clear.ui.admission.view.AdmissionFragment
import java.util.*
import kotlin.collections.ArrayList

class AdmissionContentVPAdapter(val data: ArrayList<Int>, val mainCont: Context, val frag: AdmissionFragment): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface listener {
        fun onClick(binding: ItemAdmissionAdmisPageBinding)
    }

    lateinit var itemListener: listener

    fun setListener(listenData: listener) {
        itemListener = listenData
    }

    override fun getItemViewType(position: Int): Int {
        return data[position]
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                val binding = ItemAdmissionAdmisPageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AdmissionHolder(binding)
            }
            else -> {
                val binding = ItemAdmissionWaitingPageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                WaitingHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (data[position]) {
            1 -> {
                (holder as AdmissionContentVPAdapter.AdmissionHolder).init()
            }
            else -> {
                (holder as AdmissionContentVPAdapter.WaitingHolder).init()
            }
        }
    }

    override fun getItemCount(): Int {
        ContextCompat.checkSelfPermission(mainCont, Manifest.permission.READ_EXTERNAL_STORAGE)
        return 2
    }

    inner class AdmissionHolder(val binding: ItemAdmissionAdmisPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun init() {

            binding.itemAdmisBeforeDesIv.setOnClickListener {
                itemListener.onClick(binding)
            }
        }
    }


    inner class WaitingHolder(val binding: ItemAdmissionWaitingPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun init() {
            val a = ArrayList<Int>()
            a.add(2)
            a.add(1)
            a.add(1)
            a.add(2)
            a.add(1)
            binding.itemAdmisWaitingRv.adapter = AdmissionWaitingRVAdapter(a)
        }
    }
}
