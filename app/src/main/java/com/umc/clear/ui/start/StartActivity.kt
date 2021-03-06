package com.umc.clear.ui.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.clear.databinding.ActivityStartBinding
import com.umc.clear.ui.MainActivity
import com.umc.clear.ui.login.LoginActivity
import com.umc.clear.ui.signup.SignupActivity
import com.umc.clear.utils.PrefApp

class StartActivity: AppCompatActivity() {
    lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        PrefApp.pref.setPrefname("user")
        if (PrefApp.pref.getString("autoLogin") == "1") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        initListener()
    }

    fun initListener() {
        binding.startDefaultGoLoginTv.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}