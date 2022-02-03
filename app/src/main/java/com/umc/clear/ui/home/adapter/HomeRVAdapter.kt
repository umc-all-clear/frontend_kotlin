package com.umc.clear.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.core.view.doOnLayout
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.databinding.ItemHomeCalendarFrameBinding
import com.umc.clear.databinding.ItemHomeHeaderBinding
import com.umc.clear.databinding.ItemHomeRankBinding
import com.umc.clear.ui.home.view.HomeFragment
import com.umc.clear.utils.PrefApp

class HomeRVAdapter(val context: Context, val dataList: ArrayList<Int>, val fragment: Fragment):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var vpHeight: Height
    interface Height {
        fun height()
    }

    fun seth(h: Height) {
        vpHeight = h
    }

    val liveChange = MutableLiveData<Boolean>()
    override fun getItemViewType(position: Int): Int {
        return dataList[0]
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            1-> {
                val binding = ItemHomeHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                headerHolder(binding)
            }
            2-> {
                val binding = ItemHomeCalendarFrameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                calFrameHolder(binding)
            }
            else-> {
                val binding = ItemHomeRankBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                rankHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(dataList[0]) {
            1-> {
                (holder as HomeRVAdapter.headerHolder).init()
            }
            2-> {
                (holder as HomeRVAdapter.calFrameHolder).init()
            }
            else-> {
                (holder as HomeRVAdapter.rankHolder).init()
            }
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class headerHolder(private val binding: ItemHomeHeaderBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun init() {
//                binding.homeUserInfoTv.text = dataList[1].toString()
            }
    }

    inner class calFrameHolder(private val binding: ItemHomeCalendarFrameBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun init() {
                val adapter = CalendarRVAdapter(context, dataList)
                binding.homeCalVp.adapter = adapter

                adapter.getHeight()
                binding.homeCalVp.doOnLayout {
                    val rvpos = PrefApp.pref.getString("calxPos").toInt()
                    var pos = IntArray(2)
                    binding.homeCalMonTv.getLocationInWindow(pos)

                    binding.homeCalMonTv.updatePadding(rvpos, 0, 0, 0)
                    binding.homeCalSunTv.updatePadding(0, 0, rvpos + 20, 0)
                }

                binding.homeCalVp.doOnAttach {
                    binding.homeCalVp.setCurrentItem(1000, false)
                }

                binding.apply {
                        lifecycleOwner = fragment.viewLifecycleOwner
                        height = fragment as HomeFragment

                        if (position != PrefApp.glob.getCalPage()) {
                            PrefApp.glob.setCalPage(binding.homeCalVp.currentItem)
                            liveChange.value = true
                        } else {
                            liveChange.value = false
                        }
                    }
            }
    }

    inner class rankHolder(private val binding: ItemHomeRankBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun init() {
            }

    }
}