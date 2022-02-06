package com.umc.clear.ui.home.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.umc.clear.R
import com.umc.clear.databinding.FragmentHomeBinding
import com.umc.clear.ui.MainActivity
import com.umc.clear.ui.home.adapter.HomeRVAdapter
import com.umc.clear.utils.PrefApp
import java.util.*


class HomeFragment: Fragment() {
    lateinit var context: MainActivity
    lateinit var binding: FragmentHomeBinding
//    lateinit var vpHeight: Height
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val arr = ArrayList<Int>()
        arr.add(2)
        binding.homeRv.adapter = HomeRVAdapter(context, arr, this as HomeFragment)
        //init()
//        dBinding.root.doOnLayout {
//            PrefApp.glob.setElseHeight(dBinding.homeCalMonTv.height + dBinding.homeCalTv.height)
//        }


        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as MainActivity
    }
//
//    interface Height {
//        fun height()
//    }
//
//    fun seth(h: Height) {
//        vpHeight = h
//    }

//    private fun init() {
//
//        val cal = Calendar.getInstance()
//        dBinding.homeCalTv.text = cal.get(Calendar.YEAR).toString() + "년 " + (cal.get(Calendar.MONTH) + 1).toString() + "월"
//        val adapter = CalendarVPAdapter(this)
//        dBinding.homeCalVp.adapter = adapter
//
//        dBinding.homeCalVp.doOnAttach {
//            dBinding.homeCalVp.setCurrentItem(1000, false)
//        }
//
//
//
//        dBinding.homeCalVp.doOnLayout {
//
//            val rvpos = PrefApp.pref.getString("calxPos").toInt()
//            var pos = IntArray(2)
//            dBinding.homeCalMonTv.getLocationInWindow(pos)
//
//            dBinding.homeCalMonTv.updatePadding(rvpos, 0, 0, 0)
//            dBinding.homeCalSunTv.updatePadding(0, 0, rvpos + 20, 0)
//
//
//        }
//
//            dBinding.homeCalVp.registerOnPageChangeCallback(object :
//                ViewPager2.OnPageChangeCallback() {
//                override fun onPageScrolled(
//                    position: Int,
//                    positionOffset: Float,
//                    positionOffsetPixels: Int
//                ) {
//                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//
//                    val cal = Calendar.getInstance()
//                    if (position < 1000) {
//                        cal.add(Calendar.MONTH, -(1000 - position))
//                    } else if (position > 1000) {
//                        cal.add(Calendar.MONTH, position - 1000)
//                    }
//                    val year = cal.get(Calendar.YEAR)
//                    val month = cal.get(Calendar.MONTH) + 1
//                    dBinding.homeCalTv.text = year.toString() + "년 " + month.toString() + "월"
//
//                    dBinding.apply {
//                        lifecycleOwner = viewLifecycleOwner
//                        height = this@HomeFragment
//
//                        if (position != PrefApp.glob.getCalPage()) {
//                            PrefApp.glob.setCalPage(dBinding.homeCalVp.currentItem)
//                            vpHeight.height()
//                            liveChange.value = true
//                        } else {
//                            liveChange.value = false
//                        }
//                    }
//                }
//            })

//    }


    fun dpTopx(dp: Int, dpi: Float) : Int = (dp * dpi).toInt()
    fun pxTodp(px: Double, dpi: Float): Int = (px / dpi).toInt()

}