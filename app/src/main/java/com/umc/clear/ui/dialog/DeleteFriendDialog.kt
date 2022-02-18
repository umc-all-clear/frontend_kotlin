package com.umc.clear.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.umc.clear.data.local.FriendDatabase
import com.umc.clear.utils.Pair2
import com.umc.clear.databinding.DialogDeleteFriendBinding
import com.umc.clear.utils.PrefApp

class DeleteFriendDialog(val topData: ArrayList<Pair2>, val otherData: ArrayList<Pair2>, val mainContext: Context): DialogFragment() {
    lateinit var binding: DialogDeleteFriendBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogDeleteFriendBinding.inflate(inflater, container, false)

        init()

        return binding.root
    }

    fun init() {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)


        binding.dialogFriendCancelIv.setOnClickListener {
            PrefApp.glob.setDel(false)
            dismiss()
        }

        binding.dialogFriendConfirmIv.setOnClickListener {

            val db = FriendDatabase.getInstance(mainContext)!!
            for (i in topData) {
                if (i.isChecked) {
                    db.friendDao().del(i.mail)
                }
            }
            for (i in otherData) {
                if (i.isChecked) {
                    db.friendDao().del(i.mail)
                }
            }

            //서버와 data통신

            PrefApp.glob.setDel(true)
            dismiss()
        }
    }
}