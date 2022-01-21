package com.umc.clear.ui

import android.content.Context
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.umc.clear.databinding.ActivityMainBinding
import com.umc.clear.ui.home.HomeFragment
import com.umc.clear.ui.rank.RankFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var bundle = Bundle()
        var wid = 0.0
        if (Build.VERSION.SDK_INT >= 30) {
            wid = windowManager.currentWindowMetrics.bounds.right.toDouble() / 2.0
            bundle.putDouble("size", wid)
        }
        else {
            val dis = this.windowManager.defaultDisplay
            var size = Point()
            dis?.getRealSize(size)
            wid = size.x.toDouble() / 2.0
            bundle.putDouble("size", wid)
        }

        HomeFragment().arguments = bundle

        Log.d("width", bundle.toString())
        val trans = supportFragmentManager.beginTransaction()
        trans.add(binding.mainFl.id, HomeFragment())
        trans.commit()
    }
}