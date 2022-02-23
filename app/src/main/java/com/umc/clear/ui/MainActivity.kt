package com.umc.clear.ui

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import com.umc.clear.R
import com.umc.clear.databinding.ActivityMainBinding
import com.umc.clear.ui.admission.view.AdmissionFragment
import com.umc.clear.ui.friend.view.FriendFragment
import com.umc.clear.ui.home.view.HomeFragment
import com.umc.clear.ui.notify.view.NotifyFragment
import com.umc.clear.utils.PrefApp

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var home: HomeFragment
    lateinit var friend: FriendFragment
    lateinit var admis: AdmissionFragment
    lateinit var noti: NotifyFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()

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
        setDevInfo(wid)
        binding.mainNaviNv.itemIconTintList = null

        home = HomeFragment()
        friend = FriendFragment()
        admis = AdmissionFragment()
        noti = NotifyFragment()

        supportFragmentManager.beginTransaction().add(binding.mainFl.id, home).commit()
        supportFragmentManager.beginTransaction().add(binding.mainFl.id, friend).hide(friend).commit()
        supportFragmentManager.beginTransaction().add(binding.mainFl.id, admis).hide(admis).commit()
        supportFragmentManager.beginTransaction().add(binding.mainFl.id, noti).hide(noti).commit()

        initListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        PrefApp.pref.setPrefname("user")
        if (PrefApp.pref.getString("autoLogin") == "0" || PrefApp.pref.getString("saveId") == "0") {
            PrefApp.pref.delUserInfo()
        }
        if (PrefApp.pref.getString("saveId") == "1") {
            val id = PrefApp.pref.getString("email")
            PrefApp.pref.delUserInfo()
            PrefApp.pref.putString("saveId", "1")
            PrefApp.pref.putString("email", id)
        }
    }

    fun setDevInfo(px: Int) {
        PrefApp.pref.setPrefname("deviceInfo")
        PrefApp.pref.putString("dpi", resources.displayMetrics.density.toString())
        PrefApp.pref.putString("widPx", px.toString())
    }

    fun setFragment(pos: Int) {
        val fragMang = supportFragmentManager.beginTransaction()
        when (pos) {
            1-> {
                fragMang.hide(friend)
                fragMang.hide(admis)
                fragMang.hide(noti)

                fragMang.show(home).commit()

                binding.mainNaviNv.menu.findItem(R.id.nav_friend).isChecked = false
                binding.mainNaviNv.menu.findItem(R.id.nav_admission).isChecked = false
                binding.mainNaviNv.menu.findItem(R.id.nav_notify).isChecked = false
                binding.mainNaviNv.menu.findItem(R.id.nav_home).isChecked = true
            }
            2-> {
                fragMang.hide(home)
                fragMang.hide(admis)
                fragMang.hide(noti)

                fragMang.show(friend).commit()

                binding.mainNaviNv.menu.findItem(R.id.nav_home).isChecked = false
                binding.mainNaviNv.menu.findItem(R.id.nav_admission).isChecked = false
                binding.mainNaviNv.menu.findItem(R.id.nav_notify).isChecked = false
                binding.mainNaviNv.menu.findItem(R.id.nav_friend).isChecked = true
            }
            3-> {
                fragMang.hide(home)
                fragMang.hide(friend)
                fragMang.hide(noti)

                fragMang.show(admis).commit()

                binding.mainNaviNv.menu.findItem(R.id.nav_home).isChecked = false
                binding.mainNaviNv.menu.findItem(R.id.nav_friend).isChecked = false
                binding.mainNaviNv.menu.findItem(R.id.nav_notify).isChecked = false
                binding.mainNaviNv.menu.findItem(R.id.nav_admission).isChecked = true
            }
            else-> {
                fragMang.hide(home)
                fragMang.hide(friend)
                fragMang.hide(admis)

                fragMang.show(noti).commit()

                binding.mainNaviNv.menu.findItem(R.id.nav_home).isChecked = false
                binding.mainNaviNv.menu.findItem(R.id.nav_friend).isChecked = false
                binding.mainNaviNv.menu.findItem(R.id.nav_admission).isChecked = false
                binding.mainNaviNv.menu.findItem(R.id.nav_notify).isChecked = true

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