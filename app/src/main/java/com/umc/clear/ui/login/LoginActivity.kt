package com.umc.clear.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.umc.clear.R
import com.umc.clear.data.entities.GetLogin
import com.umc.clear.data.entities.GetSignup
import com.umc.clear.data.entities.ReqLogin
import com.umc.clear.data.entities.ReqSignup
import com.umc.clear.data.remote.RetroService
import com.umc.clear.databinding.ActivityLoginBinding
import com.umc.clear.ui.MainActivity
import com.umc.clear.ui.signup.SignupActivity
import com.umc.clear.utils.PrefApp

class LoginActivity: AppCompatActivity(), LoginView {
    lateinit var binding: ActivityLoginBinding
    var autoLogin = false
    var saveId = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initListener()

        PrefApp.pref.setPrefname("user")
        if (PrefApp.pref.getString("saveId") == "1") {
            binding.loginIdEt.setText(PrefApp.pref.getString("email"))
            saveId = true
            binding.loginIdSaveIv.setImageResource(R.drawable.dialog_add_friend_check_selected)
        }
    }

    private fun initListener() {
        binding.loginGoSignupTv.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.loginAutoLoginIv.setOnClickListener {
            if (autoLogin) {
                autoLogin = false
                binding.loginAutoLoginIv.setImageResource(R.drawable.item_calendar_circle_disable)
            }
            else {
                autoLogin = true
                binding.loginAutoLoginIv.setImageResource(R.drawable.dialog_add_friend_check_selected)
            }
        }

        binding.loginIdSaveIv.setOnClickListener {
            if (saveId) {
                saveId = false
                binding.loginIdSaveIv.setImageResource(R.drawable.item_calendar_circle_disable)
            }
            else {
                saveId = true
                binding.loginIdSaveIv.setImageResource(R.drawable.dialog_add_friend_check_selected)
            }
        }

        binding.loginIdEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.loginIdEt.text.isBlank()) {
                    binding.loginIdDelIv.visibility = View.GONE
                }
                else {
                    binding.loginIdDelIv.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.loginPwEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.loginPwEt.text.isBlank()) {
                    binding.loginPwDelIv.visibility = View.GONE
                }
                else {
                    binding.loginPwDelIv.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.loginIdDelIv.setOnClickListener {
            binding.loginIdEt.text.clear()
        }

        binding.loginPwDelIv.setOnClickListener {
            binding.loginPwEt.text.clear()
        }

        binding.loginGoLoginTv.setOnClickListener {
            val email = binding.loginIdEt.text
            val pw = binding.loginPwEt.text

            if (email.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "모든 필드를 채워주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                val serv = RetroService
                serv.setlData(this)

                var req = ReqLogin(email.toString(), pw.toString())
                RetroService.reqLogin(req)
            }
        }
    }

    override fun onLoginGetSuccess(data: GetLogin) {
        PrefApp.pref.setPrefname("user")
        PrefApp.pref.putString("index", data.result?.id.toString())
        PrefApp.pref.putString("email", data.result?.email.toString())
        PrefApp.pref.putString("nic", data.result?.nickname.toString())


        PrefApp.pref.setPrefname("user")
        if (autoLogin) {
            PrefApp.pref.putString("autoLogin", "1")
        }

        else {
            PrefApp.pref.putString("autoLogin", "0")
        }

        if (saveId) {
            PrefApp.pref.putString("saveId", "1")
        }
        else {
            PrefApp.pref.putString("saveId", "0")
        }


        //get values
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginGetFailure(code: String) {
        Toast.makeText(this, "오류 발생" + code, Toast.LENGTH_SHORT).show()
    }
}