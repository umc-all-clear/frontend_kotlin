package com.umc.clear.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.clear.databinding.FragmentHomeCalendarEmptyBinding
import java.util.*

class CalendarEmptyFragment(val date: ArrayList<Int>): Fragment() {
    lateinit var binding: FragmentHomeCalendarEmptyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeCalendarEmptyBinding.inflate(inflater, container, false)

        init()

        return binding.root
    }

    fun init() {
        val cal = Calendar.getInstance()
        cal.set(date[1], date[2], date[0])

        val y = cal.get(Calendar.YEAR).toString()
        val m = (cal.get(Calendar.MONTH) + 1).toString()
        val d = cal.get(Calendar.DATE)
        val day = getDay(cal.get(Calendar.DAY_OF_WEEK))

        binding.itemCalDesEmptyDateTv.text = y + "년 " + m + "월 " + d + "일 (" + day + ")"
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