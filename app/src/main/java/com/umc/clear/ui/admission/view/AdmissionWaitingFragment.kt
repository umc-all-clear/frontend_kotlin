package com.umc.clear.ui.admission.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.clear.databinding.FragmentAdmissionWaitingBinding

class AdmissionWaitingFragment: Fragment() {
    lateinit var binding: FragmentAdmissionWaitingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdmissionWaitingBinding.inflate(inflater, container, false)

        initListener()
        return binding.root
    }

    fun initListener() {
        binding.itemAdmisDialCloseIv.setOnClickListener {
            binding.itemAdmisDialCl.visibility = View.GONE
            binding.admisBlurTv.visibility = View.GONE
        }
    }
}