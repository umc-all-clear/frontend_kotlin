package com.umc.clear.ui.home.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.clear.data.entities.*
import com.umc.clear.data.local.FriendDatabase
import com.umc.clear.data.remote.RetroService
import com.umc.clear.databinding.FragmentHomeBinding
import com.umc.clear.ui.MainActivity
import com.umc.clear.ui.dialog.AddFriendView
import com.umc.clear.ui.friend.adapter.FriendRankRVAdapter
import com.umc.clear.ui.friend.view.FriendView
import com.umc.clear.ui.home.adapter.HomeRVAdapter
import com.umc.clear.utils.PrefApp
import java.util.*


class HomeFragment: Fragment() {
    lateinit var mainCont: MainActivity
    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val arr = ArrayList<Int>()
    arr.add(1)
        arr.add(2)
    arr.add(3)
        binding.homeRv.adapter = HomeRVAdapter(mainCont, arr, this)
        //init()
//        dBinding.root.doOnLayout {
//            PrefApp.glob.setElseHeight(dBinding.homeCalMonTv.height + dBinding.homeCalTv.height)
//        }


        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mainCont = context as MainActivity
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (!hidden) {
        }
    }

    fun goTrans(id: Int, frag: Fragment) {
        val trans = this.childFragmentManager.beginTransaction()
        trans.add(id, frag)
        trans.commit()
    }

    fun goFriend() {
        mainCont.setFragment(2)
    }


}