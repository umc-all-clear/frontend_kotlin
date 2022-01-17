package com.umc.clear.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.clear.databinding.ActivityMainBinding
import com.umc.clear.ui.home.HomeFragment
import com.umc.clear.ui.rank.RankFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val trans = supportFragmentManager.beginTransaction()
        trans.add(binding.mainFl.id, HomeFragment())
        trans.commit()

    }
}