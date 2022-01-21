package com.umc.clear.ui.home

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.clear.databinding.FragmentHomeBinding
import com.umc.clear.ui.MainActivity
import com.umc.clear.utils.CalendarVPAdapter

class HomeFragment: Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var context: MainActivity
    var width: Int = 0
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
        var wid = arguments?.getDouble("size")
        if (wid != null) {
            width = wid.toInt()
        }
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

        binding.homeCalVp.adapter = CalendarVPAdapter(arr, context, width)
        binding.homeCalVp.currentItem = 1000
    }

}