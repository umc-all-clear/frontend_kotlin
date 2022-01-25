package com.umc.clear.ui.start

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.clear.databinding.ActivityStartBinding

class StartActivity: AppCompatActivity() {
    lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
    }

    fun initListener() {
        binding.startDefaultGoLoginCv.setOnClickListener {
        }
    }
}