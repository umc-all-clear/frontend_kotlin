package com.umc.clear.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.core.view.doOnLayout
import androidx.core.view.doOnNextLayout
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.umc.clear.R
import com.umc.clear.databinding.ItemHomeCalendarBinding
import com.umc.clear.ui.MainActivity
import com.umc.clear.utils.CalendarDateRVAdatper
import com.umc.clear.utils.CustomItem
import com.umc.clear.utils.CustomLinearLayout
import com.umc.clear.utils.PrefApp
import kotlin.concurrent.thread

class CalendarFragment(val calList: ArrayList<Int>, val context: FragmentActivity, val par: HomeFragment): Fragment() {
    lateinit var binding : ItemHomeCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemHomeCalendarBinding.inflate(inflater, container, false)

            par.seth(object : HomeFragment.Height {
            override fun height() {
                getHeight()
            }

        })
        thread (start= true) {
            setFL()
        }

        setRVAdapter()
        binding.root.doOnLayout {
            getHeight()
        }


        return binding.root
    }

    fun getHeight() {
            binding.root.doOnLayout {
                var vpTotalHeight = binding.itemCalDes1Fl.height +
                        binding.itemCalDes2Fl.height +
                        binding.itemCalDes3Fl.height +
                        binding.itemCalDes4Fl.height +
                        binding.itemCalDes5Fl.height +
                        binding.itemCalDes6Fl.height +
                        binding.itemCalDate1Rv.height +
                        binding.itemCalDate2Rv.height +
                        binding.itemCalDate3Rv.height +
                        binding.itemCalDate4Rv.height +
                        binding.itemCalDate5Rv.height +
                        binding.itemCalDate6Rv.height

                PrefApp.glob.setCalHeight(vpTotalHeight)
            }

    }

    private fun setRVAdapter() {

        val context = context as Context
        binding.itemCalDate1Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate1Rv.setHasFixedSize(true)
        binding.itemCalDate2Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate2Rv.setHasFixedSize(true)
        binding.itemCalDate3Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate3Rv.setHasFixedSize(true)
        binding.itemCalDate4Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate4Rv.setHasFixedSize(true)
        if (calList[4] > 4) {
            binding.itemCalDate5Rv.layoutManager = CustomLinearLayout(context)
            binding.itemCalDate5Rv.setHasFixedSize(true)
        }
        else {
            binding.itemCalDate5Rv.visibility = View.GONE
        }
        if (calList[4] > 5) {
            binding.itemCalDate6Rv.layoutManager = CustomLinearLayout(context)
            binding.itemCalDate6Rv.setHasFixedSize(true)
        }
        else {
            binding.itemCalDate6Rv.visibility = View.GONE
        }

        val width = PrefApp.pref.getString("widPx").toInt()
        val dpi = PrefApp.pref.getString("dpi").toFloat()
        val siz = (width - (dpTopx(67, dpi) * 2)) / 7

        var rvList = getRVList()

            binding.itemCalDate1Rv.addItemDecoration(CustomItem(siz))
            val adapter1 = CalendarDateRVAdatper(rvList[0])
            adapter1.setListener(object : CalendarDateRVAdatper.onclickListener {
                override fun onClick() {
                    binding.itemCalDes1Fl.visibility = View.VISIBLE
                }
            })
            binding.itemCalDate1Rv.adapter = adapter1


            binding.itemCalDate2Rv.addItemDecoration(CustomItem(siz))

            binding.itemCalDate2Rv.adapter = CalendarDateRVAdatper(rvList[1])


        binding.itemCalDate3Rv.addItemDecoration(CustomItem(siz))
            binding.itemCalDate3Rv.adapter = CalendarDateRVAdatper(rvList[2])

        binding.itemCalDate4Rv.addItemDecoration(CustomItem(siz))
            binding.itemCalDate4Rv.adapter = CalendarDateRVAdatper(rvList[3])

        if (calList[4] > 4) {
            binding.itemCalDate5Rv.addItemDecoration(CustomItem(siz))
                binding.itemCalDate5Rv.adapter = CalendarDateRVAdatper(rvList[4])

        }
        if (calList[4] > 5) {
            binding.itemCalDate6Rv.addItemDecoration(CustomItem(siz))
                binding.itemCalDate6Rv.adapter = CalendarDateRVAdatper(rvList[5])

        }
    }

    fun setFL() {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes1Fl, true)

        inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes2Fl, true)
        inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes3Fl, true)
        inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes4Fl, true)
        if (calList[4] > 4) {
            inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes5Fl, true)
        }
        if (calList[4] > 5) {
            inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes6Fl, true)
        }

    }

    private fun getRVList():ArrayList<Pair<Int, Int>> {
        var rvList = ArrayList<Pair<Int, Int>>()

        var p: Pair<Int, Int> = Pair(calList[5], 1)
        rvList.add(p)
        var twoStart = calList[5] + 1
        for (i in 2..calList[4]) {
            p = Pair(0, twoStart)
            rvList.add(p)
            twoStart += 7
        }
        return rvList
    }

    fun dpTopx(dp: Int, dpi: Float) : Int = (dp * dpi).toInt()

    fun pxTodp(px: Double, dpi: Float): Int = (px / dpi).toInt()
}