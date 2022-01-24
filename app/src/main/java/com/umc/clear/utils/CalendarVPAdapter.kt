package com.umc.clear.utils

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.umc.clear.R
import com.umc.clear.databinding.ItemHomeCalendarBinding
import com.umc.clear.ui.MainActivity
import com.umc.clear.ui.home.CalendarFragment

class CalendarVPAdapter(val dataList: ArrayList<Int>, val context: FragmentActivity):
    FragmentStateAdapter(context)
{

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    override fun createFragment(position: Int): Fragment {
        return CalendarFragment(position, dataList, context)
    }
}