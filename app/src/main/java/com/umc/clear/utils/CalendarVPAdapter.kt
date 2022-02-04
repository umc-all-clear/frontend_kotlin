//package com.umc.clear.utils
//
//import androidx.fragment.app.Fragment
//import androidx.viewpager2.adapter.FragmentStateAdapter
//import com.umc.clear.ui.home.view.CalendarFragment
//import com.umc.clear.ui.home.view.HomeFragment
//import java.util.*
//import kotlin.collections.ArrayList
//
//class CalendarVPAdapter(val context: Fragment):
//    FragmentStateAdapter(context)
//{
//    private lateinit var fragId: Fragment
//
//    override fun getItemCount(): Int {
//        return 2000
//    }
//
//    fun getFragId(): Fragment {
//        return fragId
//    }
//    override fun createFragment(position: Int): Fragment {
//
//        //pos 1000 == currentTime
//        val cal = Calendar.getInstance()
//        if (position < 1000) {
//            cal.add(Calendar.MONTH, -(1000 - position))
//        }
//        else if (position > 1000) {
//            cal.add(Calendar.MONTH, position - 1000)
//        }
//        val year = cal.get(Calendar.YEAR)
//        val month = cal.get(Calendar.MONTH) + 1
//        val weekday = cal.get(Calendar.DAY_OF_WEEK)
//
//        val totalDate = calTotalDate(year, month)
//
//        val lineAndGap = calLine(totalDate, weekday)
//
//        var calList = ArrayList<Int>()
//        calList.add(year)
//        calList.add(month)
//        calList.add(totalDate)
//        calList.add(weekday)
//        calList.add(lineAndGap.first)
//        calList.add(lineAndGap.second)
//
//        val par = context as HomeFragment
//
//        return CalendarFragment(calList, context.requireActivity(), par)
//    }
//
//    private fun calLine(total: Int, w: Int):Pair<Int, Int> {
//        var firstGap = 0
//        var line = if (w == 1) {
//            firstGap = 1
//            if ((total  - 1) % 7 == 0) {
//                (total - 1)/ 7 + 1
//            }
//            else {
//                (total - 1)/ 7 + 2
//            }
//        }
//        else if (w == 2) {
//            firstGap = 7
//            if (total % 7 == 0) {
//                total/ 7
//            }
//            else {
//                total/ 7 + 1
//            }
//        }
//        else if (w == 3) {
//            firstGap = 6
//            if ((total  - 6) % 7 == 0) {
//                (total - 6)/ 7 + 1
//            }
//            else {
//                (total - 6)/ 7 + 2
//            }
//        }
//        else if (w == 4) {
//            firstGap = 5
//            if ((total  - 5) % 7 == 0) {
//                (total - 5)/ 7 + 1
//            }
//            else {
//                (total - 5)/ 7 + 2
//            }
//        }
//        else if (w == 5) {
//            firstGap = 4
//            if ((total  - 4) % 7 == 0) {
//                (total - 4)/ 7 + 1
//            }
//            else {
//                (total - 4)/ 7 + 2
//            }
//        }
//        else if (w == 6) {
//            firstGap = 3
//            if ((total  - 3) % 7 == 0) {
//                (total - 3)/ 7 + 1
//            }
//            else {
//                (total - 3)/ 7 + 2
//            }
//        }
//        else {
//            firstGap = 2
//            if ((total  - 2) % 7 == 0) {
//                (total - 2)/ 7 + 1
//            }
//            else {
//                (total - 2)/ 7 + 2
//            }
//        }
//        return Pair(line, firstGap)
//    }
//
//    private fun calTotalDate(y: Int, m: Int): Int {
//        var total = 0
//        if (m == 2) {
//            total = if (y % 4 == 0 && y % 100 != 0) {
//                29
//            }
//            else if (y % 100 == 0 && y % 400 != 0) {
//                28
//            }
//            else if (y % 400 == 0) {
//                29
//            }
//            else
//             {
//                28
//            }
//        }
//        else {
//            if (m < 8) {
//                total = if (m%2 == 0) {
//                    30
//                } else {
//                    31
//                }
//            }
//            else {
//                total = if (m%2 == 0) {
//                    31
//                } else {
//                    30
//                }
//            }
//        }
//
//        return total
//    }
//}