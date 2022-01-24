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

class CalendarFragment(val position: Int, val dataList: ArrayList<Int>, val context: FragmentActivity): Fragment() {
    lateinit var binding : ItemHomeCalendarBinding
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
    fun setRVAdapter() {

        val context = context as Context
        binding.itemCalDate1Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate1Rv.setHasFixedSize(true)
        binding.itemCalDate2Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate2Rv.setHasFixedSize(true)
        binding.itemCalDate3Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate3Rv.setHasFixedSize(true)
        binding.itemCalDate4Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate4Rv.setHasFixedSize(true)
        binding.itemCalDate5Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate5Rv.setHasFixedSize(true)
        binding.itemCalDate6Rv.layoutManager = CustomLinearLayout(context)
        binding.itemCalDate6Rv.setHasFixedSize(true)

        val width = PrefApp.pref.getString("widPx").toInt()
        val dpi = PrefApp.pref.getString("dpi").toFloat()
        val siz = (width - (dpTopx(67, dpi) * 2)) / 7

        binding.itemCalDate1Rv.addItemDecoration(CustomItem(siz))
        val adapter1 = CalendarDateRVAdatper(dataList)
        adapter1.setListener(object :CalendarDateRVAdatper.onclickListener {
            override fun onClick() {
            }
        })
        binding.itemCalDate1Rv.adapter = adapter1
        binding.itemCalDate2Rv.addItemDecoration(CustomItem(siz))
        binding.itemCalDate2Rv.adapter = CalendarDateRVAdatper(dataList)
        binding.itemCalDate3Rv.addItemDecoration(CustomItem(siz))
        binding.itemCalDate3Rv.adapter = CalendarDateRVAdatper(dataList)
        binding.itemCalDate4Rv.addItemDecoration(CustomItem(siz))
        binding.itemCalDate4Rv.adapter = CalendarDateRVAdatper(dataList)
        binding.itemCalDate5Rv.addItemDecoration(CustomItem(siz))
        binding.itemCalDate5Rv.adapter = CalendarDateRVAdatper(dataList)
        binding.itemCalDate6Rv.addItemDecoration(CustomItem(siz))
        binding.itemCalDate6Rv.adapter = CalendarDateRVAdatper(dataList)
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

    fun dpTopx(dp: Int, dpi: Float) : Int = (dp * dpi).toInt()

    fun pxTodp(px: Double, dpi: Float): Int = (px / dpi).toInt()
}