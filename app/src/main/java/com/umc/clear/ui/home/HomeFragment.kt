package com.umc.clear.ui.home

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import com.umc.clear.databinding.FragmentHomeBinding
import com.umc.clear.ui.MainActivity
import com.umc.clear.utils.CalendarVPAdapter
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.doOnLayout
import androidx.core.view.updatePadding
import com.umc.clear.utils.PrefApp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment: Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var context: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        init()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as MainActivity
    }

    private fun init() {
        val arr = ArrayList<Int>()
        arr.add(1)
        arr.add(2)
        arr.add(3)
        arr.add(1)
        arr.add(2)
        arr.add(3)
        arr.add(3)

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        binding.homeCalVp.adapter = CalendarVPAdapter(arr, context)
        binding.homeCalVp.currentItem = 1000

        binding.homeCalVp.doOnLayout {

            val rvpos = PrefApp.pref.getString("calxPos").toInt()
            val dpi = PrefApp.pref.getString("dpi").toFloat()
            var pos = IntArray(2)
            binding.homeCalMonTv.getLocationInWindow(pos)

            val rpos = rvpos - pos[0]
            binding.homeCalMonTv.updatePadding(rvpos, 0, 0, 0)
            binding.homeCalSunTv.updatePadding(0, 0, rvpos + 20, 0)
        }
    }


    fun dpTopx(dp: Int, dpi: Float) : Int = (dp * dpi).toInt()
    fun pxTodp(px: Double, dpi: Float): Int = (px / dpi).toInt()

}