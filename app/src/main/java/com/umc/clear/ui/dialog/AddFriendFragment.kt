package com.umc.clear.ui.dialog

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.umc.clear.R
import com.umc.clear.data.entities.GetConn
import com.umc.clear.data.entities.GetFriendConn
import com.umc.clear.data.entities.ReqConn
import com.umc.clear.data.entities.ReqFriendConn
import com.umc.clear.data.remote.RetroService
import com.umc.clear.databinding.DialogAddFriendBinding
import com.umc.clear.utils.PrefApp
import kotlin.concurrent.thread

class AddFriendFragment(val mainCont: Context): Fragment(), AddFriendView, SetFriendView{
    lateinit var binding: DialogAddFriendBinding
    var isFriend = false
    var noUser = false
    var searchId = 0
    var tempMail = ""
    var tempNic = ""
    var tempIdx = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogAddFriendBinding.inflate(inflater, container, false)

        init()

        PrefApp.pref.setPrefname("user")
        binding.dialogAddFriendMymailTv.text = PrefApp.pref.getString("email")
        val retro = RetroService
        retro.setfcData(this)
        return binding.root
    }

    fun init() {
        binding.dialogAddFriendMailEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.dialogAddFriendDesTv.text == "정보가 없습니다. 이메일을 다시 검색해주세요") {
                    binding.dialogAddFriendDesTv.visibility = View.GONE
                    binding.dialogAddFriendMailCl.setBackgroundResource(R.color.white)
                }
                if (!binding.dialogAddFriendMailEt.text.isBlank()) {
                    binding.dialogAddFriendMailSearchIv.visibility = View.VISIBLE
                }
                else {
                    binding.dialogAddFriendMailSearchIv.visibility = View.GONE
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.dialogAddFriendMailSearchIv.setOnClickListener {
            PrefApp.pref.setPrefname("user")
            RetroService.reqConn(ReqConn(binding.dialogAddFriendMailEt.text.toString(), PrefApp.pref.getString("email")))

            thread(start = true) {
                while (binding.dialogAddFriendPb.visibility != View.GONE) {
                }
                binding.root.post {
                    if (noUser) {
                        noUser = false
                        binding.dialogAddFriendMailCl.setBackgroundResource(R.drawable.item_red_boarder)
                        binding.dialogAddFriendDesTv.text = "정보가 없습니다. 이메일을 다시 검색해주세요"
                        binding.dialogAddFriendDesTv.setTextColor(Color.parseColor("#ff3120"))

                        binding.dialogAddFriendDesTv.visibility = View.VISIBLE
                    } else if (!isFriend) {
                        binding.dialogAddFriendMailCv.visibility = View.GONE
                        binding.dialogAddFriendProfileContentCheckIv.visibility = View.GONE

                        binding.dialogAddFriendProfileContentMailTv.text = tempMail
                        binding.dialogAddFriendProfileContentNameTv.text = tempNic

                        binding.dialogAddFriendProfileContentCv.visibility = View.VISIBLE
                        binding.dialogAddFriendCancelTv.visibility = View.VISIBLE
                        binding.dialogAddFriendAddTv.visibility = View.VISIBLE
                    } else {
                        binding.dialogAddFriendMailCl.setBackgroundResource(R.drawable.item_red_boarder)
                        binding.dialogAddFriendDesTv.text = "이미 친구입니다"
                        binding.dialogAddFriendDesTv.setTextColor(Color.parseColor("#ff3120"))

                        binding.dialogAddFriendDesTv.visibility = View.VISIBLE
                    }
                }
            }
        }

        binding.dialogAddFriendMymailDupIv.setOnClickListener {
            Toast.makeText(mainCont, "이메일이 복사되었습니다", Toast.LENGTH_SHORT).show()
        }

        binding.dialogAddFriendAddTv.setOnClickListener {
            val serv = RetroService
            serv.setfsData(this)

            PrefApp.pref.setPrefname("user")
            RetroService.reqFriendConn(ReqFriendConn(PrefApp.pref.getString("email"), binding.dialogAddFriendProfileContentMailTv.text.toString()))

            binding.dialogAddFriendCancelTv.visibility = View.GONE
            binding.dialogAddFriendAddTv.visibility = View.GONE

            binding.dialogAddFriendProfileContentCheckIv.visibility = View.VISIBLE
            binding.dialogAddFriendDesTv.text = "추가가 완료되었습니다!"
            binding.dialogAddFriendDesTv.setTextColor(Color.parseColor("#007aff"))
            binding.dialogAddFriendDesTv.visibility = View.VISIBLE
        }

        binding.dialogAddFriendCancelTv.setOnClickListener {
            binding.dialogAddFriendProfileContentCv.visibility = View.GONE
            binding.dialogAddFriendCancelTv.visibility = View.GONE
            binding.dialogAddFriendAddTv.visibility = View.GONE

            binding.dialogAddFriendMailEt.setText("")
            binding.dialogAddFriendMailCv.visibility = View.VISIBLE
        }
    }

    override fun onConnGetSuccess(data: GetConn) {
        if (data?.result?.get(0)?.state == 0) {
            isFriend = false
            tempMail = data?.result?.get(0)?.friendEmail!!
            tempNic = data?.result?.get(0)?.friendNickname!!
            tempIdx = data?.result?.get(0)?.friendIndex!!
        }
        else {
            isFriend = true
        }
        binding.dialogAddFriendPb.visibility = View.GONE
    }

    override fun onConnGetFailure(code: String) {
        noUser = true
        binding.dialogAddFriendPb.visibility = View.GONE
    }

    override fun onConnGetLoading() {
        binding.dialogAddFriendPb.visibility = View.VISIBLE
    }

    override fun onFriendConnGetSuccess(data: GetFriendConn) {
    }

    override fun onFriendConnGetFailure(code: String) {
    }
}