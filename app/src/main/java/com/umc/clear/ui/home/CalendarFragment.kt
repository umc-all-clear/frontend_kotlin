package com.umc.clear.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.umc.clear.R
import com.umc.clear.databinding.ItemHomeCalendarBinding
import com.umc.clear.utils.CalendarDateRVAdatper
import com.umc.clear.utils.CustomItem
import com.umc.clear.utils.CustomLinearLayout
import com.umc.clear.utils.PrefApp

class CalendarFragment(val calList: ArrayList<Int>, val context: FragmentActivity): Fragment() {
    lateinit var binding : ItemHomeCalendarBinding
    val year = calList[0]
    val mon = calList[1]

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemHomeCalendarBinding.inflate(inflater, container, false)
        setFL()
        setRVAdapter()

        return binding.root
    }

    private fun setRVAdapter() {

        val context = context as Context
        binding.itemCalDate1Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate1Rv.setHasFixedSize(true)
        binding.itemCalDate2Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate2Rv.setHasFixedSize(true)
        binding.itemCalDate3Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate3Rv.setHasFixedSize(true)
        if (calList[4] > 4) {
            binding.itemCalDate4Rv.layoutManager = CustomLinearLayout(context)
            binding.itemCalDate4Rv.setHasFixedSize(true)
        } else {
            binding.itemCalDate4Rv.visibility = View.GONE
        }
        if (calList[4] > 5) {
            binding.itemCalDate5Rv.layoutManager = CustomLinearLayout(context)
            binding.itemCalDate5Rv.setHasFixedSize(true)
        } else {
            binding.itemCalDate5Rv.visibility = View.GONE
        }
        binding.itemCalDate6Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate6Rv.setHasFixedSize(true)

        val width = PrefApp.pref.getString("widPx").toInt()
        val dpi = PrefApp.pref.getString("dpi").toFloat()
        val siz = (width - (dpTopx(67, dpi) * 2)) / 7

        var rvList = getRVList()

        binding.itemCalDate1Rv.addItemDecoration(CustomItem(siz))
        val adapter1 = CalendarDateRVAdatper(rvList[0])
        adapter1.setListener(object : CalendarDateRVAdatper.onclickListener {
            override fun onClick() {
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
        inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes5Fl, true)
        inflater.inflate(R.layout.item_home_calendar_des, binding.itemCalDes6Fl, true)

    }

    private fun getRVList():ArrayList<Pair<Int, Int>> {
        var rvList = ArrayList<Pair<Int, Int>>()

        var p: Pair<Int, Int> = Pair(calList[5], 1)
        rvList.add(p)
        var twoStart = calList[5]
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