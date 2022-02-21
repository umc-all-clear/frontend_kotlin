package com.umc.clear.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.umc.clear.R
import com.umc.clear.databinding.FragmentHomeCalendarDesBinding
import com.umc.clear.ui.home.adapter.DescriptionCommRVAdapter
import com.umc.clear.ui.home.adapter.DescriptionImgRVAdapter
import com.umc.clear.ui.home.adapter.HomeRVAdapter
import com.umc.clear.utils.PrefApp
import java.util.*
import kotlin.collections.ArrayList

class CalendarDescriptionFragment(val date: ArrayList<Int>, val parFrag: HomeRVAdapter): Fragment() {
    lateinit var binding: FragmentHomeCalendarDesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeCalendarDesBinding.inflate(inflater, container, false)

        init()
        return binding.root
    }

    fun init() {
        val cal = Calendar.getInstance()
        cal.set(date[1], date[2] - 1, date[0])

        val y = cal.get(Calendar.YEAR).toString()
        val m = (cal.get(Calendar.MONTH) + 1).toString()
        val d = cal.get(Calendar.DATE)
        val day = getDay(cal.get(Calendar.DAY_OF_WEEK))

        binding.calendarDesDateTv.text = y + "년 " + m + "월 " + d + "일 (" + day + ")"


        var arrInt = ArrayList<Int>()
        var arrStr = ArrayList<String>()
        arrStr.add("asdf")
        arrStr.add("aaa")
        arrStr.add("qwetg")
        arrStr.add("asdgawe")
        arrStr.add("asdggsade")

        arrInt.add(R.drawable.icon)
        arrInt.add(R.drawable.ic_launcher_foreground)
        arrInt.add(R.drawable.ic_launcher_background)
        arrInt.add(R.drawable.ic_launcher_foreground)
        arrInt.add(R.drawable.ic_launcher_background)

        val beforeAdapter = DescriptionImgRVAdapter(arrInt)
        binding.calendarDesBeforeRvv.adapter = beforeAdapter

        val afterAdapter = DescriptionImgRVAdapter(arrInt)
        binding.calendarDesAfterRvv.adapter = afterAdapter

        val userAdapter = DescriptionCommRVAdapter(arrStr)
        binding.calendarDesComRvv.adapter = userAdapter

        val adminAdapter = DescriptionCommRVAdapter(arrStr)
        binding.calendarDesFeedRvv.adapter = adminAdapter

        syncItem()
    }

    private fun syncItem() {
        binding.calendarDesBeforeRvv.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                binding.calendarDesAfterRvv.currentItem = position
                binding.calendarDesComRvv.currentItem = position
                binding.calendarDesFeedRvv.currentItem = position
            }
        }
        )

        binding.calendarDesAfterRvv.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                binding.calendarDesBeforeRvv.currentItem = position
                binding.calendarDesComRvv.currentItem = position
                binding.calendarDesFeedRvv.currentItem = position


            }
        })

        binding.calendarDesComRvv.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.calendarDesBeforeRvv.currentItem = position
                binding.calendarDesAfterRvv.currentItem = position
                binding.calendarDesFeedRvv.currentItem = position
            }
        })

        binding.calendarDesFeedRvv.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.calendarDesBeforeRvv.currentItem = position
                binding.calendarDesAfterRvv.currentItem = position
                binding.calendarDesComRvv.currentItem = position
            }
        })

        binding.calendarDesCloseIv.setOnClickListener {
            binding.root.visibility = View.GONE
            PrefApp.glob.closeDate()
            parFrag.liveChange()
        }
    }

    fun getDay(day: Int): String {
        return when (day) {
            1-> {
                "일"
            }
            2-> {
                "월"
            }
            3-> {
                "화"
            }
            4-> {
                "수"
            }
            5-> {
                "목"
            }
            6-> {
                "금"
            }
            else-> {
                "토"
            }
        }
    }


}