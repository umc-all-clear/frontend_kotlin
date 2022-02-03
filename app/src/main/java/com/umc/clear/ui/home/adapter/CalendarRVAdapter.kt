package com.umc.clear.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.R
import com.umc.clear.databinding.ItemHomeCalendarBinding
import com.umc.clear.utils.CalendarDateRVAdatper
import com.umc.clear.utils.CustomItem
import com.umc.clear.utils.CustomLinearLayout
import com.umc.clear.utils.PrefApp
import java.util.*
import kotlin.collections.ArrayList

class CalendarRVAdapter(val context: Context, val dataList: ArrayList<Int>): RecyclerView.Adapter<CalendarRVAdapter.ViewHolder>() {
    lateinit var binding: ItemHomeCalendarBinding
    var calList = ArrayList<Int>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarRVAdapter.ViewHolder {
        binding = ItemHomeCalendarBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarRVAdapter.ViewHolder, position: Int) {
        holder.init(position)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    inner class ViewHolder(val binding: ItemHomeCalendarBinding): RecyclerView.ViewHolder(binding.root) {
        fun init(pos: Int) {
            setDate(pos)
            getHeight()
            setFL()
            setRVAdapter()
        }
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

    private fun setRVAdapter() {
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

    fun setDate(position: Int) {//pos 1000 == currentTime
        val cal = Calendar.getInstance()
        if (position < 1000) {
            cal.add(Calendar.MONTH, -(1000 - position))
        }
        else if (position > 1000) {
            cal.add(Calendar.MONTH, position - 1000)
        }
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) + 1
        val weekday = cal.get(Calendar.DAY_OF_WEEK)

        val totalDate = calTotalDate(year, month)

        val lineAndGap = calLine(totalDate, weekday)

        calList.add(year)
        calList.add(month)
        calList.add(totalDate)
        calList.add(weekday)
        calList.add(lineAndGap.first)
        calList.add(lineAndGap.second)
    }

    private fun calLine(total: Int, w: Int):Pair<Int, Int> {
        var firstGap = 0
        var line = if (w == 1) {
            firstGap = 1
            if ((total  - 1) % 7 == 0) {
                (total - 1)/ 7 + 1
            }
            else {
                (total - 1)/ 7 + 2
            }
        }
        else if (w == 2) {
            firstGap = 7
            if (total % 7 == 0) {
                total/ 7
            }
            else {
                total/ 7 + 1
            }
        }
        else if (w == 3) {
            firstGap = 6
            if ((total  - 6) % 7 == 0) {
                (total - 6)/ 7 + 1
            }
            else {
                (total - 6)/ 7 + 2
            }
        }
        else if (w == 4) {
            firstGap = 5
            if ((total  - 5) % 7 == 0) {
                (total - 5)/ 7 + 1
            }
            else {
                (total - 5)/ 7 + 2
            }
        }
        else if (w == 5) {
            firstGap = 4
            if ((total  - 4) % 7 == 0) {
                (total - 4)/ 7 + 1
            }
            else {
                (total - 4)/ 7 + 2
            }
        }
        else if (w == 6) {
            firstGap = 3
            if ((total  - 3) % 7 == 0) {
                (total - 3)/ 7 + 1
            }
            else {
                (total - 3)/ 7 + 2
            }
        }
        else {
            firstGap = 2
            if ((total  - 2) % 7 == 0) {
                (total - 2)/ 7 + 1
            }
            else {
                (total - 2)/ 7 + 2
            }
        }
        return Pair(line, firstGap)
    }

    private fun calTotalDate(y: Int, m: Int): Int {
        var total = 0
        if (m == 2) {
            total = if (y % 4 == 0 && y % 100 != 0) {
                29
            }
            else if (y % 100 == 0 && y % 400 != 0) {
                28
            }
            else if (y % 400 == 0) {
                29
            }
            else
            {
                28
            }
        }
        else {
            if (m < 8) {
                total = if (m%2 == 0) {
                    30
                } else {
                    31
                }
            }
            else {
                total = if (m%2 == 0) {
                    31
                } else {
                    30
                }
            }
        }

        return total
    }
}