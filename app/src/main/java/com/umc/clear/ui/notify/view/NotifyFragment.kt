package com.umc.clear.ui.notify.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.clear.databinding.FragmentNotifyBinding

class NotifyFragment: Fragment() {
    lateinit var binding: FragmentNotifyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotifyBinding.inflate(inflater, container, false)

        return binding.root
    }
}