package com.umc.clear.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.umc.clear.R
import com.umc.clear.data.entities.GetConn
import com.umc.clear.data.entities.GetFriendsRank
import com.umc.clear.data.entities.ReqConn
import com.umc.clear.data.entities.ReqFriendsRank
import com.umc.clear.data.remote.RetroService
import com.umc.clear.databinding.ItemHomeCalendarFrameBinding
import com.umc.clear.databinding.ItemHomeHeaderBinding
import com.umc.clear.databinding.ItemHomeRankBinding
import com.umc.clear.ui.dialog.AddFriendView
import com.umc.clear.ui.dialog.SetupDialog
import com.umc.clear.ui.friend.view.FriendView
import com.umc.clear.ui.home.view.HomeFragment
import com.umc.clear.utils.PrefApp
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class HomeRVAdapter(val mainCont: Context, val dataList: ArrayList<Int>, val fragment: HomeFragment):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val liveVpChange = MutableLiveData<Boolean>()
    val liveCvChange = MutableLiveData<Boolean>()
    var firstCall = true
    var vpButtonCall = false
    var tmpRank = ""

    override fun getItemViewType(position: Int): Int {
        return dataList[position]
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            1-> {
                val binding = ItemHomeHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderHolder(binding)
            }
            2-> {
                val binding = DataBindingUtil.inflate<ItemHomeCalendarFrameBinding>(LayoutInflater.from(parent.context), R.layout.item_home_calendar_frame, parent, false)
                CalFrameHolder(binding)
            }
            else-> {
                val binding = ItemHomeRankBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RankHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(dataList[position]) {
            1-> {
                (holder as HomeRVAdapter.HeaderHolder).bind()
            }
            2-> {
                (holder as HomeRVAdapter.CalFrameHolder).bind()
            }
            else-> {
                (holder as HomeRVAdapter.RankHolder).bind()
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    inner class HeaderHolder(private val binding: ItemHomeHeaderBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind() {

                binding.homeUserInfoTv.text = "username"

                PrefApp.pref.setPrefname("user")
                binding.homeUserInfoTv.text = PrefApp.pref.getString("nic")

                binding.homeUserInfoAddIv.setOnClickListener {
                    SetupDialog(fragment, mainCont).show(fragment.childFragmentManager.beginTransaction(), "SetupDialog")

                }
            }


    }



    inner class CalFrameHolder(private val binding: ItemHomeCalendarFrameBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind() {

                binding.homeCalVp.isUserInputEnabled = false

                binding.homeCalMonTv.post {
                    PrefApp.glob.setRvHeight(binding.homeCalMonTv.width)
                    binding.homeCalMonTv.height = PrefApp.glob.getRvHeight()
                }
                binding.homeCalNextIv.setOnClickListener {
                    vpButtonCall = true
                    binding.homeCalVp.setCurrentItem(binding.homeCalVp.currentItem + 1, false)
                }
                binding.homeCalPrevIv.setOnClickListener {
                    vpButtonCall = true
                    binding.homeCalVp.setCurrentItem(binding.homeCalVp.currentItem - 1, false)
                }

                binding.root.doOnLayout {
                    PrefApp.glob.setElseHeight(binding.homeCalMonTv.height + binding.homeCalTv.height)
                }

                val adapter = CalendarRVAdapter(mainCont, this@HomeRVAdapter, binding)
                binding.homeCalVp.adapter = adapter


                thread (start = true) {
                    while (binding.homeCalMonTv.width == 0) {
                    }

                    binding.homeCalMonTv.height = binding.homeCalMonTv.width
//                    val rvpos = PrefApp.pref.getString("calxPos").toInt()
//                    var pos = IntArray(2)
//                    binding.homeCalMonTv.getLocationInWindow(pos)
//
//                    binding.homeCalMonTv.updatePadding(left = rvpos + pos[0] - dpTopx(15, PrefApp.pref.getString("dpi").toFloat()))
//                    binding.homeCalSunTv.updatePadding(right = rvpos + pos[0])

                }


                binding.homeCalVp.doOnAttach {
                    binding.homeCalVp.setCurrentItem(1000, false)
                }


                binding.homeCalVp.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                        if (vpButtonCall) {
                            vpButtonCall = false
                            onPageSelected(position)
                        }
                    }
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)

                        if (vpButtonCall) {
                            return
                        }

                            binding.apply {

                                PrefApp.glob.closeDate()

                                val cal = Calendar.getInstance()
                                if (position < 1000) {
                                    cal.add(Calendar.MONTH, -(1000 - position))
                                } else if (position > 1000) {
                                    cal.add(Calendar.MONTH, position - 1000)
                                }
                                val year = cal.get(Calendar.YEAR)
                                val month = cal.get(Calendar.MONTH) + 1
                                binding.homeCalTv.text =
                                    year.toString() + "년 " + month.toString() + "월"


                                lifecycleOwner = fragment.viewLifecycleOwner
                                height = this@HomeRVAdapter

//                                if (!firstCall) {
//                                    adapter.setHeight(position, month)
//                                }
                                firstCall = false
                                if (position != PrefApp.glob.getCalPage()) {
                                    adapter.setHeight(position, month, 0)
                                    PrefApp.glob.setCalPage(position)
                                    liveCvChange.value = true
                                    liveVpChange.value = true
                                    liveCvChange.value = false
                                    liveVpChange.value = false
                                } else {
                                    liveCvChange.value = false
                                    liveVpChange.value = false
                                }
                            }
                        }


                })
            }
    }

    inner class RankHolder(private val binding: ItemHomeRankBinding)
        : RecyclerView.ViewHolder(binding.root), FriendView, AddFriendView {
            fun bind() {
                setRank()

                var param = binding.root.layoutParams as ViewGroup.MarginLayoutParams
                PrefApp.pref.setPrefname("deviceInfo")
                param.setMargins(0, dpTopx(40, PrefApp.pref.getString("dpi").toFloat()), 0, dpTopx(40, PrefApp.pref.getString("dpi").toFloat()))
                binding.root.layoutParams = param

                val cal = Calendar.getInstance()

                val month = cal.get(Calendar.MONTH) + 1

                binding.homeFriendTv.text = month.toString() + "월의 1등"

                binding.homeFriendMoreTv.setOnClickListener {
                    fragment.goFriend()
                }
                ///1등 가져오기
            }

        fun setRank() {
            val cal = Calendar.getInstance()

            PrefApp.pref.setPrefname("user")
            PrefApp.pref.getString("email")
            var req = ReqFriendsRank(
                PrefApp.pref.getString("index").toInt(),
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1
            )

            val conn = RetroService
            conn.setfData(this)
            conn.reqRank(req)
        }

        override fun onRankGetFailure(code: String) {
        }

        override fun onRankGetSuccess(data: GetFriendsRank) {
            val friendArr = data.result

            if (friendArr?.size != 0) {

                tmpRank = friendArr?.get(0)?.score.toString()
                val conn = RetroService
                conn.setfcData(this)

                PrefApp.pref.setPrefname("user")
                conn.reqConn(
                    ReqConn(
                        PrefApp.pref.getString("email"),
                        friendArr?.get(0)?.friendEmail!!
                    ), ""
                )
            }
            else {
                binding.homeFriendNameTv.text = "친구가 없음..."
            }
        }

        override fun onConnGetSuccess(data: GetConn, score: String?) {
            binding.homeFriendNameTv.text = data.result?.get(0)?.friendNickname
            binding.homeFriendMailTv.text = data.result?.get(0)?.friendEmail
            binding.homeFriendRateTv.text = tmpRank
        }

        override fun onConnGetFailure(code: String) {
        }

        override fun onConnGetLoading() {
        }

    }

    fun liveChange() {
        liveCvChange.postValue(true)
        liveVpChange.postValue(true)
        liveCvChange.postValue(false)
        liveVpChange.postValue(false)
    }

    fun dpTopx(dp: Int, dpi: Float) : Int = (dp * dpi).toInt()

    fun pxTodp(px: Double, dpi: Float): Int = (px / dpi).toInt()


}