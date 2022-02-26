package com.umc.clear.ui.admission.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.umc.clear.R
import com.umc.clear.data.entities.GetAdmission
import com.umc.clear.databinding.FragmentAdmissionBinding
import com.umc.clear.databinding.ItemAdmissionAdmisPageBinding
import com.umc.clear.ui.MainActivity
import com.umc.clear.ui.admission.adapter.AdmissionContentVPAdapter
import com.umc.clear.ui.dialog.SetupDialog
import com.umc.clear.utils.PrefApp
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class AdmissionFragment: Fragment(), AdmissionView {
    lateinit var binding: FragmentAdmissionBinding
    lateinit var contentBinding: ItemAdmissionAdmisPageBinding
    lateinit var mainCont: Context
    lateinit var beforeUri: Uri
    lateinit var afterUri: Uri

    var isBefore = true
    val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            output: ActivityResult-> setPhoto(output)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdmissionBinding.inflate(inflater, container, false)

        initListener()

        PrefApp.pref.setPrefname("user")
        binding.admisNameTv.text = PrefApp.pref.getString("nic")
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
        val adapter = AdmissionContentVPAdapter(a, mainCont, this)
        adapter.setListener(object : AdmissionContentVPAdapter.listener {
            override fun onClick(binding: ItemAdmissionAdmisPageBinding, isBefore: Boolean) {
                getPhoto(binding, isBefore)
            }
        })
        binding.admisContentVp.adapter = adapter
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

    fun getPhoto(cBinding: ItemAdmissionAdmisPageBinding, isBefore: Boolean) {
        val int = Intent(Intent.ACTION_PICK)
        int.type = MediaStore.Images.Media.CONTENT_TYPE
        int.type = "image/*"

        contentBinding = cBinding
        this.isBefore = isBefore
        getContent.launch(int)
    }

    fun setPhoto(output: ActivityResult) {
        if (isBefore) {
            contentBinding.itemAdmisBeforeDesCl.setPadding(0, 0, 0, 0)
            contentBinding.itemAdmisBeforeDesIv.visibility = View.GONE
            contentBinding.itemAdmisBeforeDesTv.visibility = View.GONE

            contentBinding.itemAdmisBeforeIv.setImageURI(output.data?.data)
            contentBinding.itemAdmisBeforeIv.tag = "true"
            beforeUri = output.data?.data!!
        }
        else {
            contentBinding.itemAdmisAfterDesIv.visibility = View.GONE
            contentBinding.itemAdmisAfterDesTv.visibility = View.GONE
            contentBinding.itemAdmisAfterDesCl.setPadding(0, 0, 0, 0)

            contentBinding.itemAdmisAfterIv.setImageURI(output.data?.data)
            contentBinding.itemAdmisAfterIv.tag = "true"
            afterUri = output.data?.data!!
        }
    }

    override fun onAdmissionGetSuccess(data: GetAdmission) {
    }

    override fun onAdmissionGetFailure(code: String) {
        Log.d("retroFail", code)
    }
}