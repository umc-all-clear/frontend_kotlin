package com.umc.clear.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.umc.clear.R
import com.umc.clear.databinding.DialogSetupBinding
import com.umc.clear.ui.home.view.CalendarDescriptionFragment
import com.umc.clear.ui.home.view.HomeFragment
import com.umc.clear.utils.PrefApp

class SetupDialog(val fragment: Fragment): DialogFragment() {
    lateinit var binding: DialogSetupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogSetupBinding.inflate(inflater, container, false)

        init()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val w = PrefApp.pref.getString("widPx").toInt() - 100
        val h = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(w, h)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = false
    }
    fun init() {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        val fl = childFragmentManager?.beginTransaction()
        val item = AddFriendFragment()
        fl?.add(binding.dialogSetupContentFl.id, item)
        fl?.commit()

        binding.dialogSetupExitIv.setOnClickListener {
            dismiss()
        }
    }
}