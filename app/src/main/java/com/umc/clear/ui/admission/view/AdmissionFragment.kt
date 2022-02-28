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
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.umc.clear.R
import com.umc.clear.data.entities.GetAdmission
import com.umc.clear.data.entities.GetData
import com.umc.clear.data.entities.ReqData
import com.umc.clear.data.entities.dataResult
import com.umc.clear.data.remote.RetroService
import com.umc.clear.databinding.FragmentAdmissionBinding
import com.umc.clear.databinding.ItemAdmissionAdmisPageBinding
import com.umc.clear.ui.MainActivity
import com.umc.clear.ui.admission.adapter.AdmissionContentVPAdapter
import com.umc.clear.ui.dialog.SetupDialog
import com.umc.clear.utils.PrefApp
import java.util.*
import kotlin.collections.ArrayList

class AdmissionFragment: Fragment(), AdmissionView, DataView {
    lateinit var binding: FragmentAdmissionBinding
    lateinit var contentBinding: ItemAdmissionAdmisPageBinding
    lateinit var adapter: AdmissionContentVPAdapter
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
        adapter = AdmissionContentVPAdapter(a, mainCont, this)
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

            val conn = RetroService
            conn.setData(this)

            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH) + 1

            PrefApp.pref.setPrefname("user")
            conn.reqData(PrefApp.pref.getString("email"), ReqData(year, month))
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

        binding.itemAdmisDialCloseIv.setOnClickListener {
            binding.itemAdmisDialCl.visibility = View.GONE
            binding.admisBlurTv.visibility = View.GONE
        }


        binding.itemWaitingContentCl.setOnClickListener {
            binding.itemAdmisDialTitleTv.text = timeArr[0] + ":" + timeArr[1] + "에 신청한 사진"

            Glide.with(frag.requireContext()).load(data.beforePicUrl).into(binding.itemAdmisDialBeforeIv)
            Glide.with(frag.requireContext()).load(data.afterPicUrl).into(binding.itemAdmisDialAfterIv)
            binding.itemAdmisDialCommTv.text = data.contents

            binding.itemAdmisDialCl.visibility = View.VISIBLE
            binding.admisBlurTv.visibility = View.VISIBLE
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
        contentBinding.itemAdmisAfterDesIv.visibility = View.VISIBLE
        contentBinding.itemAdmisAfterDesTv.visibility = View.VISIBLE
        contentBinding.itemAdmisBeforeDesIv.visibility = View.VISIBLE
        contentBinding.itemAdmisBeforeDesTv.visibility = View.VISIBLE
        contentBinding.itemAdmisBeforeIv.tag = "false"
        contentBinding.itemAdmisAfterIv.tag = "false"
        contentBinding.itemAdmisBeforeDesCl.setPadding(0, 80, 0, 70)
        contentBinding.itemAdmisAfterDesCl.setPadding(0, 80, 0, 70)
        contentBinding.itemAdmisBeforeIv.setImageDrawable(null)
        contentBinding.itemAdmisAfterIv.setImageDrawable(null)
        contentBinding.itemAdmisCommEt.text.clear()


        Toast.makeText(mainCont, "신청완료!", Toast.LENGTH_SHORT).show()
    }

    override fun onAdmissionGetFailure(code: String) {
        Log.d("retroFail", code)
    }

    override fun onDataGetSuccess(data: GetData) {
        PrefApp.glob.setDataArr(data.result as ArrayList<dataResult>)

        adapter.notifyItemChanged(1)

    }

    override fun onDataGetFailure(code: String) {
    }
}