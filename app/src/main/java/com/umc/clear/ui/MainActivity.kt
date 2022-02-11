package com.umc.clear.ui

import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import com.umc.clear.databinding.ActivityMainBinding
import com.umc.clear.ui.admission.view.AdmissionFragment
import com.umc.clear.ui.friend.view.FriendFragment
import com.umc.clear.ui.home.view.HomeFragment
import com.umc.clear.utils.PrefApp

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var home: HomeFragment
    lateinit var friend: FriendFragment
    lateinit var admis: AdmissionFragment

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

        binding.mainNaviNv.itemIconTintList = null

        home = HomeFragment()
        friend = FriendFragment()
        admis = AdmissionFragment()

        supportFragmentManager.beginTransaction().add(binding.mainFl.id, home).commit()
        supportFragmentManager.beginTransaction().add(binding.mainFl.id, friend).hide(friend).commit()
        supportFragmentManager.beginTransaction().add(binding.mainFl.id, admis).hide(admis).commit()

        initListener()
        supportActionBar?.hide()
        setDevInfo(wid)
    }

    fun setDevInfo(px: Int) {
        PrefApp.pref.putString("dpi", resources.displayMetrics.density.toString())
        PrefApp.pref.putString("widPx", px.toString())
    }

    fun setFragment(pos: Int) {
        if (friend == null) {
            friend = FriendFragment()
            supportFragmentManager.beginTransaction().add(binding.mainFl.id, friend).commit()
        }
        val fragMang = supportFragmentManager.beginTransaction()
        when (pos) {
            1-> {
                fragMang.hide(friend)
                fragMang.hide(admis)

                fragMang.show(home).commit()
            }
            2-> {
                fragMang.hide(home)
                fragMang.hide(admis)

                fragMang.show(friend).commit()
            }
            3-> {
                fragMang.hide(home)
                fragMang.hide(friend)

                fragMang.show(admis).commit()
            }
        }

    }

    fun initListener() {
        binding.mainNaviNv.setOnItemSelectedListener {
            setFragment(
            if (it.title == "홈") {
                1
            }
            else if (it.title == "친구") {
                2
            }
            else if (it.title == "신청"){
                3
            }
            else {
                4
            }
            )
            true
        }
    }
}