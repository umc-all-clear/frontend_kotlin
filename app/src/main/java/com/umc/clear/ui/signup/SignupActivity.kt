package com.umc.clear.ui.signup

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.umc.clear.data.entities.GetSignup
import com.umc.clear.data.entities.ReqSignup
import com.umc.clear.data.remote.RetroService
import com.umc.clear.databinding.ActivitySignupBinding

class SignupActivity: AppCompatActivity(), SignupView {
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.siginupGoSiginupTv.setOnClickListener {

            val serv = RetroService
            serv.setData(this)

            var req = ReqSignup("asdf@naver.com", "11111!111", "11111!111", "teasgqw")
            RetroService.reqSignin(req)
        }
    }

    override fun onLoginGetFailure() {
    }

    override fun onLoginGetSuccess(data: GetSignup) {
        Log.d("loginData", data.toString())
        Log.d("loginData", data.toString())
    }
}