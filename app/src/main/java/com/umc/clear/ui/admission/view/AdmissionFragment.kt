package com.umc.clear.ui.admission.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.clear.R
import com.umc.clear.databinding.FragmentAdmissionBinding
import com.umc.clear.ui.MainActivity
import com.umc.clear.ui.admission.adapter.AdmissionContentVPAdapter
import com.umc.clear.ui.dialog.SetupDialog

class AdmissionFragment: Fragment() {
    lateinit var binding: FragmentAdmissionBinding
    lateinit var mainCont: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdmissionBinding.inflate(inflater, container, false)

        initListener()

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainCont = context as MainActivity
    }

    fun initListener() {
        val a = ArrayList<Int>()
        a.add(1)
        a.add(2)
        binding.admisContentVp.adapter = AdmissionContentVPAdapter(a)
        binding.admisContentVp.isUserInputEnabled = false

        binding.admisWaitingCv.setOnClickListener {
            if (binding.admisContentVp.currentItem == 0) {
                binding.admisWaitingIv.setImageResource(R.drawable.fragment_admission_go_waiting_selected)
                binding.admisWaitingCl.setBackgroundColor(resources.getColor(R.color.black))
                binding.admisWaitingTv.setTextColor(resources.getColor(R.color.white))

                binding.admisAdmissionIv.setImageResource(R.drawable.fragment_admission_go_admis_unselected)
                binding.admisAdmissionCl.setBackgroundColor(resources.getColor(R.color.white))
                binding.admisAdmissionTv.setTextColor(resources.getColor(R.color.black))

                binding.admisContentVp.currentItem = 1
            }
        }

        binding.admisAdmissionCv.setOnClickListener {
            if (binding.admisContentVp.currentItem == 1) {
                binding.admisWaitingIv.setImageResource(R.drawable.activity_main_admission_unselected)
                binding.admisWaitingCl.setBackgroundColor(resources.getColor(R.color.white))
                binding.admisWaitingTv.setTextColor(resources.getColor(R.color.black))


                binding.admisAdmissionIv.setImageResource(R.drawable.fragment_admission_go_admis_selected)
                binding.admisAdmissionCl.setBackgroundColor(resources.getColor(R.color.black))
                binding.admisAdmissionTv.setTextColor(resources.getColor(R.color.white))

                binding.admisContentVp.currentItem = 0
            }
        }

        binding.admisSetupIv.setOnClickListener {
            SetupDialog(this, mainCont).show(this.childFragmentManager.beginTransaction(), "SetupDialog")
        }

        binding.admisMoreIv.setOnClickListener {
            SetupDialog(this, mainCont).show(this.childFragmentManager.beginTransaction(), "SetupDialog")
        }
    }
}