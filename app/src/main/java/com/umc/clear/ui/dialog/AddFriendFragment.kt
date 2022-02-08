package com.umc.clear.ui.dialog

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.clear.R
import com.umc.clear.databinding.DialogAddFriendBinding

class AddFriendFragment: Fragment() {
    lateinit var binding: DialogAddFriendBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogAddFriendBinding.inflate(inflater, container, false)

        init()
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
            val data = true
            if (data) {
                binding.dialogAddFriendMailCv.visibility = View.GONE
                binding.dialogAddFriendProfileContentCheckIv.visibility = View.GONE

                binding.dialogAddFriendProfileContentCv.visibility = View.VISIBLE
                binding.dialogAddFriendCancelTv.visibility = View.VISIBLE
                binding.dialogAddFriendAddTv.visibility = View.VISIBLE
            }
            else {
                binding.dialogAddFriendMailCl.setBackgroundResource(R.drawable.item_red_boarder)
                binding.dialogAddFriendDesTv.text = "정보가 없습니다. 이메일을 다시 검색해주세요"
                binding.dialogAddFriendDesTv.setTextColor(Color.parseColor("#ff3120"))

                binding.dialogAddFriendDesTv.visibility = View.VISIBLE
            }
        }

        binding.dialogAddFriendAddTv.setOnClickListener {
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
}