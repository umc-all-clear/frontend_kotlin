package com.umc.clear.ui

import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import com.umc.clear.databinding.ActivityMainBinding
import com.umc.clear.ui.home.view.HomeFragment
import com.umc.clear.utils.PrefApp

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var wid = 0

        val met = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= 30) {
            val dis = windowManager.currentWindowMetrics
            wid = dis.bounds.width()
        }
        else {
            val dis = this.windowManager.defaultDisplay
            var size = Point()
            dis?.getRealSize(size)
            wid = size.x.toInt()
        }

        val home = HomeFragment()


        setDevInfo(wid)

        val trans = supportFragmentManager.beginTransaction()
        trans.add(binding.mainFl.id, home)
        trans.commit()
    }

    fun setDevInfo(px: Int) {
        PrefApp.pref.putString("dpi", resources.displayMetrics.density.toString())
        PrefApp.pref.putString("widPx", px.toString())
    }
}