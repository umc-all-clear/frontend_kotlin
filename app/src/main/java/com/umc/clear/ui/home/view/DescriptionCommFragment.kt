package com.umc.clear.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.clear.databinding.ItemHomeCalendarDesCommBinding

class DescriptionCommFragment(val text: String): Fragment() {
    lateinit var binding: ItemHomeCalendarDesCommBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemHomeCalendarDesCommBinding.inflate(inflater, container, false)


        binding.itemCommTv.text = text
        return binding.root
    }
}