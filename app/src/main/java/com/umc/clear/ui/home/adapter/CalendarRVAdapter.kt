package com.umc.clear.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.databinding.ItemHomeCalendarBinding
import com.umc.clear.databinding.ItemHomeCalendarFrameBinding
import com.umc.clear.ui.home.view.CalendarDescriptionFragment
import com.umc.clear.ui.home.view.CalendarEmptyFragment
import com.umc.clear.utils.CustomItem
import com.umc.clear.utils.CustomLinearLayout
import com.umc.clear.utils.PrefApp
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class CalendarRVAdapter(val mainCont: Context, val par: HomeRVAdapter, val parBinding: ItemHomeCalendarFrameBinding): RecyclerView.Adapter<CalendarRVAdapter.ViewHolder>() {
    var flHeight = 0
    var firstCall = true
    var firstContentFrameCall = true
    var firstEmptyFrameCall = true

    var calList = ArrayList<ArrayList<Int>>()
    var calData = ArrayList<ArrayList<ArrayList<Int>>>()
    lateinit var desFragmentItem: CalendarDescriptionFragment
    lateinit var desEmptyFragmentItem: CalendarEmptyFragment

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarRVAdapter.ViewHolder {
        val binding = ItemHomeCalendarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        if (firstCall) {
            for (i in 1..2000) {
                calData.add(ArrayList())
                calList.add(ArrayList())
            }
        }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarRVAdapter.ViewHolder, position: Int) {
        holder.init(position)
    }

    override fun getItemCount(): Int {
        return 2000
    }

    inner class ViewHolder(val binding: ItemHomeCalendarBinding): RecyclerView.ViewHolder(binding.root) {
        fun init(pos: Int) {
            setDate(pos)
            if (calData[pos].isEmpty()) {
                //pos해당하는 data호출
                    val dat = 2
                var tmpArr = ArrayList<Int>()
                for (i in 1..dat) {
                    //input data
                }
            }
            setHeight(pos, calList[pos][1], 0)
            setRVAdapter(pos,binding)
            par.liveChange()
            par.liveChange()
        }
    }

    fun setHeight(pos: Int, month: Int, frame: Int) {
         if (month == calList[pos][1]) {
             if (firstCall) {

                 parBinding.homeCalMonTv.post {
                     while (PrefApp.glob.getRvHeight() == 0) {

                     }
                     PrefApp.glob.setCalHeight(calList[pos][4], frame)
                 }
                 firstCall = false
             }
             else {
                 PrefApp.glob.setCalHeight(calList[pos][4], frame)
         }
//            if (binding.itemCalDate5Rv.visibility == View.GONE) {
//                PrefApp.glob.setCalHeight(4)
//            } else if (binding.itemCalDate6Rv.visibility == View.GONE) {
//                PrefApp.glob.setCalHeight(5)
//            } else {
//                PrefApp.glob.setCalHeight(6)
//            }
        }
    }

    fun setFL(pos: Int, binding: ItemHomeCalendarBinding, line:Int, data: Boolean, date: ArrayList<Int>) {

        if (line == 1) {
            if (data) {
                val item = CalendarDescriptionFragment(date, par)
                par.fragment.goTrans(binding.itemCalDes1Fl.id, item)
                desFragmentItem = item
            }
            else {
                val item = CalendarEmptyFragment(date, par)
                par.fragment.goTrans(binding.itemCalDes1Fl.id, item)
                desEmptyFragmentItem = item
            }
        }
        else if (line == 2) {
            if (data) {
                val item = CalendarDescriptionFragment(date, par)
                par.fragment.goTrans(binding.itemCalDes2Fl.id, item)
                desFragmentItem = item
            }
            else {
                val item = CalendarEmptyFragment(date, par)
                par.fragment.goTrans(binding.itemCalDes2Fl.id, item)
                desEmptyFragmentItem = item
            }
        }
        else if (line == 3) {
            if (data) {
                val item = CalendarDescriptionFragment(date, par)
                par.fragment.goTrans(binding.itemCalDes3Fl.id, item)
                desFragmentItem = item
            }
            else {
                val item = CalendarEmptyFragment(date, par)
                par.fragment.goTrans(binding.itemCalDes3Fl.id, item)
                desEmptyFragmentItem = item
            }
        }
        else if (line == 4) {
            if (data) {
                val item = CalendarDescriptionFragment(date, par)
                par.fragment.goTrans(binding.itemCalDes4Fl.id, item)
                desFragmentItem = item
            }
            else {
                val item = CalendarEmptyFragment(date, par)
                par.fragment.goTrans(binding.itemCalDes4Fl.id, item)
                desEmptyFragmentItem = item
            }
        }
        else if (line == 5) {
            if (data) {
                val item = CalendarDescriptionFragment(date, par)
                par.fragment.goTrans(binding.itemCalDes5Fl.id, item)
                desFragmentItem = item
            }
            else {
                val item = CalendarEmptyFragment(date, par)
                par.fragment.goTrans(binding.itemCalDes5Fl.id, item)
                desEmptyFragmentItem = item
            }
        }
        else if (line == 6) {
            if (data) {
                val item = CalendarDescriptionFragment(date, par)
                par.fragment.goTrans(binding.itemCalDes6Fl.id, item)
                desFragmentItem = item
            }
            else {
                val item = CalendarEmptyFragment(date, par)
                par.fragment.goTrans(binding.itemCalDes6Fl.id, item)
                desEmptyFragmentItem = item
            }
        }
//
//
//        inflater.inflate(R.layout.item_home_calendar_des_content, binding.itemCalDes2Fl, true)
//        inflater.inflate(R.layout.item_home_calendar_des_content, binding.itemCalDes3Fl, true)
//        inflater.inflate(R.layout.item_home_calendar_des_content, binding.itemCalDes4Fl, true)
//        if (calList[pos][4] > 4) {
//            inflater.inflate(R.layout.item_home_calendar_des_content, binding.itemCalDes5Fl, true)
//        }
//        if (calList[pos][4] > 5) {
//            inflater.inflate(R.layout.item_home_calendar_des_content, binding.itemCalDes6Fl, true)
//        }




    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.binding.itemCalDes1Cv.visibility = View.GONE
        holder.binding.itemCalDes2Cv.visibility = View.GONE
        holder.binding.itemCalDes3Cv.visibility = View.GONE
        holder.binding.itemCalDes4Cv.visibility = View.GONE
        holder.binding.itemCalDes5Cv.visibility = View.GONE
        holder.binding.itemCalDes6Cv.visibility = View.GONE
    }

    fun setFlHeight(h: Int, f: Boolean) {
        if (f) {
            if (firstContentFrameCall) {
                PrefApp.glob.setFlHeight(h, f)
                firstContentFrameCall = false
            }
            return
        }
        else {
            if (firstEmptyFrameCall) {
                PrefApp.glob.setFlHeight(h, f)
                firstEmptyFrameCall = false
            }
            return
        }
    }

    fun closeAllCv(binding: ItemHomeCalendarBinding) {
        binding.itemCalDes1Cv.visibility = View.GONE
        binding.itemCalDes2Cv.visibility = View.GONE
        binding.itemCalDes3Cv.visibility = View.GONE
        binding.itemCalDes4Cv.visibility = View.GONE
        binding.itemCalDes5Cv.visibility = View.GONE
        binding.itemCalDes6Cv.visibility = View.GONE
    }

    fun setRVAdapter(pos: Int, binding: ItemHomeCalendarBinding) {
        binding.itemCalDate1Rv.layoutManager = CustomLinearLayout(mainCont)
        binding.itemCalDate1Rv.setHasFixedSize(true)
        binding.itemCalDate2Rv.layoutManager = CustomLinearLayout(mainCont)
        binding.itemCalDate2Rv.setHasFixedSize(true)
        binding.itemCalDate3Rv.layoutManager = CustomLinearLayout(mainCont)
        binding.itemCalDate3Rv.setHasFixedSize(true)
        binding.itemCalDate4Rv.layoutManager = CustomLinearLayout(mainCont)
        binding.itemCalDate4Rv.setHasFixedSize(true)
        if (calList[pos][4] > 4) {
            binding.itemCalDate5Rv.visibility = View.VISIBLE
            binding.itemCalDate5Rv.layoutManager = CustomLinearLayout(mainCont)
            binding.itemCalDate5Rv.setHasFixedSize(true)
        }
        else {
            binding.itemCalDate5Rv.visibility = View.GONE
        }
        if (calList[pos][4] > 5) {
            binding.itemCalDate6Rv.visibility = View.VISIBLE
            binding.itemCalDate6Rv.layoutManager = CustomLinearLayout(mainCont)
            binding.itemCalDate6Rv.setHasFixedSize(true)
        }
        else {
            binding.itemCalDate6Rv.visibility = View.GONE
        }

        PrefApp.pref.setPrefname("deviceInfo")
        val width = PrefApp.pref.getString("widPx").toInt()
        val dpi = PrefApp.pref.getString("dpi").toFloat()
        val siz = (width - (dpTopx(67, dpi) * 2)) / 7

        val rvList = getRVList(pos)

        binding.itemCalDate1Rv.addItemDecoration(CustomItem(siz))
        val adapter1 = CalendarDateRVAdapter(rvList[0], mainCont, parBinding)
        adapter1.setListener(object : CalendarDateRVAdapter.onclickListener {
            override fun onClick(date: Int) {
                ////해당월 기반 데이터 서버호출
                var data = true
                closeAllCv(binding)
                binding.itemCalDes1Cv.visibility = View.VISIBLE
                var tmpArr = ArrayList<Int>()
                tmpArr.add(date)
                tmpArr.add(calList[pos][0])
                tmpArr.add(calList[pos][1])
                setFL(pos, binding, 1, data, tmpArr)
                binding.itemCalDes1Fl.post {
                    if (data) {
                        setFlHeight(binding.itemCalDes1Fl.height, true)
                        setHeight(pos, calList[pos][1], 2)
                        par.liveChange()
                    }
                    else {
                        setFlHeight(binding.itemCalDes1Cv.height+100, false)
                        setHeight(pos, calList[pos][1], 1)
                        par.liveChange()
                    }
                }
            }
        })

        binding.itemCalDate1Rv.adapter = adapter1


        binding.itemCalDate2Rv.addItemDecoration(CustomItem(siz))
        val adapter2 = CalendarDateRVAdapter(rvList[1], mainCont, parBinding)
        adapter2.setListener(object : CalendarDateRVAdapter.onclickListener {
            override fun onClick(date: Int) {
                ////해당월 기반 데이터 서버호출
                var data = true
                closeAllCv(binding)
                binding.itemCalDes2Cv.visibility = View.VISIBLE
                var tmpArr = ArrayList<Int>()
                tmpArr.add(date)
                tmpArr.add(calList[pos][0])
                tmpArr.add(calList[pos][1])
                setFL(pos, binding, 2, data, tmpArr)
                thread (start = true) {

                    while(binding.itemCalDes2Fl.height == 0) {
                    }
                    if (data) {
                        setFlHeight(binding.itemCalDes2Cv.height + 100, true)
                        setHeight(pos, calList[pos][1], 2)
                        par.liveChange()
                    } else {
                        setFlHeight(binding.itemCalDes2Cv.height + 100, false)
                        setHeight(pos, calList[pos][1], 1)
                        par.liveChange()
                    }
                }
            }
        })
        binding.itemCalDate2Rv.adapter = adapter2

        binding.itemCalDate3Rv.addItemDecoration(CustomItem(siz))
        val adapter3 = CalendarDateRVAdapter(rvList[2], mainCont, parBinding)
        adapter3.setListener(object : CalendarDateRVAdapter.onclickListener {
            override fun onClick(date: Int) {
                ////해당월 기반 데이터 서버호출
                var data = true
                closeAllCv(binding)
                binding.itemCalDes3Cv.visibility = View.VISIBLE
                var tmpArr = ArrayList<Int>()
                tmpArr.add(date)
                tmpArr.add(calList[pos][0])
                tmpArr.add(calList[pos][1])
                setFL(pos, binding, 3, data, tmpArr)
                thread (start = true) {

                    while(binding.itemCalDes3Fl.height == 0) {
                    }
                    if (data) {
                        setFlHeight(binding.itemCalDes3Cv.height + 100, true)
                        setHeight(pos, calList[pos][1], 2)
                        par.liveChange()
                    } else {
                        setFlHeight(binding.itemCalDes3Cv.height + 100, false)
                        setHeight(pos, calList[pos][1], 1)
                        par.liveChange()
                    }
                }
            }
        })
        binding.itemCalDate3Rv.adapter = adapter3

        binding.itemCalDate4Rv.addItemDecoration(CustomItem(siz))
        val adapter4 = CalendarDateRVAdapter(rvList[3], mainCont, parBinding)
        adapter4.setListener(object : CalendarDateRVAdapter.onclickListener {
            override fun onClick(date: Int) {
                ////해당월 기반 데이터 서버호출
                var data = true
                closeAllCv(binding)
                binding.itemCalDes4Cv.visibility = View.VISIBLE
                var tmpArr = ArrayList<Int>()
                tmpArr.add(date)
                tmpArr.add(calList[pos][0])
                tmpArr.add(calList[pos][1])
                setFL(pos, binding, 4, data, tmpArr)
                thread (start = true) {

                    while(binding.itemCalDes4Fl.height == 0) {
                    }
                    if (data) {
                        setFlHeight(binding.itemCalDes4Cv.height + 100, true)
                        setHeight(pos, calList[pos][1], 2)
                        par.liveChange()
                    } else {
                        setFlHeight(binding.itemCalDes4Cv.height + 100, false)
                        setHeight(pos, calList[pos][1], 1)
                        par.liveChange()
                    }
                }
            }
        })
        binding.itemCalDate4Rv.adapter = adapter4

        if (calList[pos][4] > 4) {
            binding.itemCalDate5Rv.addItemDecoration(CustomItem(siz))
            val adapter5 = CalendarDateRVAdapter(rvList[4], mainCont, parBinding)
            adapter5.setListener(object : CalendarDateRVAdapter.onclickListener {
                override fun onClick(date: Int) {
                    ////해당월 기반 데이터 서버호출
                    var data = true
                    closeAllCv(binding)
                    binding.itemCalDes5Cv.visibility = View.VISIBLE
                    var tmpArr = ArrayList<Int>()
                    tmpArr.add(date)
                    tmpArr.add(calList[pos][0])
                    tmpArr.add(calList[pos][1])
                    setFL(pos, binding, 5, data, tmpArr)
                    thread (start = true) {

                        while(binding.itemCalDes5Fl.height == 0) {
                        }
                        if (data) {
                            setFlHeight(binding.itemCalDes5Cv.height + 100, true)
                            setHeight(pos, calList[pos][1], 2)
                            par.liveChange()
                        } else {
                            setFlHeight(binding.itemCalDes5Cv.height + 100, false)
                            setHeight(pos, calList[pos][1], 1)
                            par.liveChange()
                        }
                    }
                }
            })
            binding.itemCalDate5Rv.adapter = adapter5

        }
        if (calList[pos][4] > 5) {
            binding.itemCalDate6Rv.addItemDecoration(CustomItem(siz))
            val adapter6 = CalendarDateRVAdapter(rvList[5], mainCont, parBinding)
            adapter6.setListener(object : CalendarDateRVAdapter.onclickListener {
                override fun onClick(date: Int) {
                    ////해당월 기반 데이터 서버호출
                    var data = true
                    closeAllCv(binding)
                    binding.itemCalDes6Cv.visibility = View.VISIBLE
                    var tmpArr = ArrayList<Int>()
                    tmpArr.add(date)
                    tmpArr.add(calList[pos][0])
                    tmpArr.add(calList[pos][1])
                    setFL(pos, binding, 6, data, tmpArr)
                    thread (start = true) {

                        while(binding.itemCalDes6Fl.height == 0) {
                        }
                        if (data) {
                            setFlHeight(binding.itemCalDes6Cv.height + 100, true)
                            setHeight(pos, calList[pos][1], 2)
                            par.liveChange()
                        } else {
                            setFlHeight(binding.itemCalDes6Cv.height + 100, false)
                            setHeight(pos, calList[pos][1], 1)
                            par.liveChange()
                        }
                    }
                }
            })
            binding.itemCalDate6Rv.adapter = adapter6
        }
    }

    private fun getRVList(pos: Int):ArrayList<ArrayList<Int>> {
        var rvList = ArrayList<ArrayList<Int>>()

        var tmpList = ArrayList<Int>()
        tmpList.add(calList[pos][5])
        tmpList.add(1)
        tmpList.add(calList[pos][4])
        tmpList.add(calList[pos][2])

        rvList.add(tmpList)

        var twoStart = calList[pos][5] + 1
        for (i in 2..calList[pos][4]) {
            var tempList = ArrayList<Int>()
            tempList.clear()
            tempList.add(0)
            tempList.add(twoStart)
            tempList.add(calList[pos][4])
            tempList.add(calList[pos][2])

            rvList.add(tempList)
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
        cal.set(Calendar.DATE, 1)

        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) + 1
        val weekday = cal.get(Calendar.DAY_OF_WEEK)

        val totalDate = calTotalDate(year, month)

        val lineAndGap = calLine(totalDate, weekday)

        var tmpCal = ArrayList<Int>()
        tmpCal.add(year)
        tmpCal.add(month)
        tmpCal.add(totalDate)
        tmpCal.add(weekday)
        tmpCal.add(lineAndGap.first)
        tmpCal.add(lineAndGap.second)

        calList[position] = tmpCal
    }

    private fun calLine(total: Int, w: Int):Pair<Int, Int> {
        var firstGap = 0
        var line = if (w == 1) {//일요일이면 그 줄에 1개 들어감
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