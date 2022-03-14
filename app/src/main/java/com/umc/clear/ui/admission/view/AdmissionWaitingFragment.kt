package com.umc.clear.ui.admission.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.umc.clear.data.entities.dataResult
import com.umc.clear.databinding.FragmentAdmissionWaitingBinding

class AdmissionWaitingFragment(timeArr: List<String>, data: dataResult, val frag: AdmissionFragment): Fragment() {
    lateinit var binding: FragmentAdmissionWaitingBinding
    var timeArr = timeArr
    var data = data

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdmissionWaitingBinding.inflate(inflater, container, false)

        init()
        initListener()
        return binding.root
    }

    fun init() {
        binding.itemAdmisDialTitleTv.text = timeArr[0] + ":" + timeArr[1] + "에 신청한 사진"

        Glide.with(this.requireContext()).load(data.beforePicUrl).into(binding.itemAdmisDialBeforeIv)
        Glide.with(this.requireContext()).load(data.afterPicUrl).into(binding.itemAdmisDialAfterIv)
        binding.itemAdmisDialCommTv.text = data.contents

    }
    fun initListener() {
        binding.itemAdmisDialCloseIv.setOnClickListener {
            frag.popHide()
        }

    }
}