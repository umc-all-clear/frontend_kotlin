package com.umc.clear.ui

import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
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

        setFullScreen()
        setDevInfo(wid)

        val trans = supportFragmentManager.beginTransaction()
        trans.add(binding.mainFl.id, home)
        trans.commit()
    }

    private fun setFullScreen() {
        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)

            val ctrl = window.insetsController

        } else {
            window.decorView.systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    fun setDevInfo(px: Int) {
        PrefApp.pref.putString("dpi", resources.displayMetrics.density.toString())
        PrefApp.pref.putString("widPx", px.toString())
    }
}