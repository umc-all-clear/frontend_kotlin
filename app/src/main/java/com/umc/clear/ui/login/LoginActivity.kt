package com.umc.clear.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.clear.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
    }

    private fun initListener() {
        binding.loginGoLoginTv.setOnClickListener {

        }
    }
}