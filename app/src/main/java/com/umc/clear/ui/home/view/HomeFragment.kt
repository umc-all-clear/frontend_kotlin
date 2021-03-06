package com.umc.clear.ui.home.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.loader.content.AsyncTaskLoader
import com.umc.clear.data.entities.*
import com.umc.clear.data.remote.RetroService
import com.umc.clear.databinding.FragmentHomeBinding
import com.umc.clear.ui.MainActivity
import com.umc.clear.ui.admission.view.DataView
import com.umc.clear.ui.home.adapter.CalendarRVAdapter
import com.umc.clear.ui.home.adapter.HomeRVAdapter
import com.umc.clear.utils.PrefApp
import io.reactivex.disposables.Disposable
import java.util.*
import kotlin.concurrent.thread


class HomeFragment: Fragment(), DataView {
    lateinit var mainCont: MainActivity
    lateinit var binding: FragmentHomeBinding
    lateinit var rvAdapter: CalendarRVAdapter

    var calData = false

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

    override fun onDestroy() {
        super.onDestroy()
    }

    fun goTrans(id: Int, frag: Fragment) {
        val trans = this.childFragmentManager.beginTransaction()
        trans.add(id, frag)
        trans.commit()
    }

    fun goFriend() {
        mainCont.setFragment(2)
    }


    fun setAdapter(adapter: CalendarRVAdapter) {
        rvAdapter = adapter

    }

    fun getData(year: Int, month: Int) {

        val retro = RetroService
        retro.setData(this)

        PrefApp.pref.setPrefname("user")
        val mail = PrefApp.pref.getString("email")

        retro.reqData(mail, ReqData(year, month), -1)
    }

    override fun onDataGetSuccess(data: GetData, order: Int) {
        val dataArr = data.result!!
        rvAdapter.onlineData = dataArr
        calData = true
    }

    override fun onDataGetFailure(code: String) {
    }


}