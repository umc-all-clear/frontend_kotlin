package com.umc.clear.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.clear.databinding.ItemHomeCalendarDesContentBinding

class CalendarDescriptionContent: Fragment() {
    lateinit var binding: ItemHomeCalendarDesContentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemHomeCalendarDesContentBinding.inflate(inflater, container, false)
        return binding.root
    }
}