package com.umc.clear.ui.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.umc.clear.data.entities.GetLogin
import com.umc.clear.data.entities.GetSignup
import com.umc.clear.data.entities.ReqSignup
import com.umc.clear.data.remote.RetroService
import com.umc.clear.databinding.ActivitySignupBinding
import com.umc.clear.ui.MainActivity
import retrofit2.Response

class SignupActivity: AppCompatActivity(), SignupView {
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initListener()
    }

    override fun onLoginGetFailure() {
    }

    override fun onLoginGetSuccess(data: GetSignup) {
        //데이터 가져오기
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun initListener() {

        binding.siginupEmailEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.siginupEmailEt.text.isBlank()) {
                    binding.siginupEmailDelIv.visibility = View.GONE
                }
                else {
                    binding.siginupEmailDelIv.visibility = View.VISIBLE
                }

            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.siginupEmailDelIv.setOnClickListener {
            binding.siginupEmailEt.text.clear()
1        }

        binding.siginupPwEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.siginupPwEt.text.isBlank()) {
                    binding.siginupPwDelIv.visibility = View.GONE
                }
                else {
                    binding.siginupPwDelIv.visibility = View.VISIBLE
                }

            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.siginupPwDelIv.setOnClickListener {
            binding.siginupPwEt.text.clear()
        }

        binding.siginupPwConfirmEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.siginupPwConfirmEt.text.isBlank()) {
                    binding.siginupPwConfirmDelIv.visibility = View.GONE
                }
                else {
                    binding.siginupPwConfirmDelIv.visibility = View.VISIBLE
                }

            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.siginupPwConfirmDelIv.setOnClickListener {
            binding.siginupPwConfirmEt.text.clear()
        }

        binding.siginupNikEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.siginupNikEt.text.isBlank()) {
                    binding.siginupNikDelIv.visibility = View.GONE
                }
                else {
                    binding.siginupNikDelIv.visibility = View.VISIBLE
                }

            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.siginupNikDelIv.setOnClickListener {
            binding.siginupNikEt.text.clear()
        }

        binding.siginupGoSiginupTv.setOnClickListener {
            val email = binding.siginupEmailEt.text
            val pw = binding.siginupPwEt.text
            val pwConfirm = binding.siginupPwConfirmEt.text
            val nic = binding.siginupNikEt.text

            if (email.isEmpty() || pw.isEmpty() || pwConfirm.isEmpty() || nic.isEmpty()) {
                Toast.makeText(this, "모든 필드를 채워주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                val serv = RetroService
                serv.setsData(this)

                var req = ReqSignup(email.toString(), pw.toString(), pwConfirm.toString(),  nic.toString())
                RetroService.reqSignin(req)
            }
        }
    }
}