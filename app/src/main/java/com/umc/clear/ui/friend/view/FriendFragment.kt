package com.umc.clear.ui.friend.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.clear.R
import com.umc.clear.data.entities.*
import com.umc.clear.data.local.FriendDatabase
import com.umc.clear.utils.Pair2
import com.umc.clear.data.remote.RetroService
import com.umc.clear.databinding.FragmentFriendBinding
import com.umc.clear.ui.dialog.AddFriendView
import com.umc.clear.ui.dialog.DeleteFriendDialog
import com.umc.clear.ui.dialog.SetupDialog
import com.umc.clear.ui.friend.adapter.FriendRankRVAdapter
import com.umc.clear.utils.PrefApp
import java.util.*
import kotlin.collections.ArrayList

class FriendFragment(): Fragment(), FriendView, AddFriendView {
    lateinit var binding: FragmentFriendBinding
    lateinit var mainContext: Context
    lateinit var rvAdapter: FriendRankRVAdapter
    var tempScore:String? = ""
    var top3Checked = ArrayList<Pair2>()
    var otherChecked = ArrayList<Pair2>()
    var isSelectMode = false

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (!isHidden)  {
            PrefApp.glob.setDel(false)

            val serv = RetroService
            serv.setfData(this)

            val cal = Calendar.getInstance()

            PrefApp.pref.setPrefname("user")
            PrefApp.pref.getString("email")
            var req = ReqFriendsRank(
                PrefApp.pref.getString("index").toInt(),
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1
            )
            RetroService.reqRank(req)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendBinding.inflate(inflater, container, false)

        for (i in 1..3) {
            val a = Pair2(false, "a")
            top3Checked.add(a)
        }

        init()
        PrefApp.pref.setPrefname("user")
        binding.friendMynameTv.text = PrefApp.pref.getString("nic")
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        if (PrefApp.glob.getDel()) {
            PrefApp.glob.setDel(false)

            val serv = RetroService
            serv.setfData(this)

            val cal = Calendar.getInstance()

            PrefApp.pref.setPrefname("user")
            PrefApp.pref.getString("email")
            var req = ReqFriendsRank(
                PrefApp.pref.getString("index").toInt(),
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1
            )
            RetroService.reqRank(req)
//
//            val db = FriendDatabase.getInstance(mainContext)!!
//            val list = db.friendDao().getAll()
//            top3Checked.clear()
//            for (i in 0..2) {
//                top3Checked.add(Pair2(false, list[i].mail))
//            }
//
//            otherChecked.clear()
//            for (i in 1..otherChecked.size - 3) {
//                otherChecked.add(Pair2(false, list[i+2].mail))
//            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainContext = context
    }

    fun init() {
        binding.friendRank1CheckIv.setOnClickListener {
            if (binding.friendRank1CheckIv.tag == "selected") {
                binding.friendRank1CheckIv.setImageResource(R.drawable.dialog_add_friend_check)
                binding.friendRank1CheckIv.setTag("unselected")
                top3Checked[0].isChecked = false
            }
            else {
                binding.friendRank1CheckIv.setImageResource(R.drawable.dialog_add_friend_check_selected)
                binding.friendRank1CheckIv.setTag("selected")
                top3Checked[0].isChecked = true
            }
        }
        binding.friendRank2CheckIv.setOnClickListener {
            if (binding.friendRank2CheckIv.tag == "selected") {
                binding.friendRank2CheckIv.setImageResource(R.drawable.dialog_add_friend_check)
                binding.friendRank2CheckIv.setTag("unselected")
                top3Checked[1].isChecked = false
            }
            else {
                binding.friendRank2CheckIv.setImageResource(R.drawable.dialog_add_friend_check_selected)
                binding.friendRank2CheckIv.setTag("selected")
                top3Checked[1].isChecked = true
            }
        }
        binding.friendRank3CheckIv.setOnClickListener {
            if (binding.friendRank3CheckIv.tag == "selected") {
                binding.friendRank3CheckIv.setImageResource(R.drawable.dialog_add_friend_check)
                binding.friendRank3CheckIv.setTag("unselected")
                top3Checked[2].isChecked = false
            }
            else {
                binding.friendRank3CheckIv.setImageResource(R.drawable.dialog_add_friend_check_selected)
                binding.friendRank3CheckIv.setTag("selected")
                top3Checked[2].isChecked = true
            }
        }

        binding.friendMoreIv.setOnClickListener {
            SetupDialog(this,mainContext).show(childFragmentManager.beginTransaction(), "SetupDialog")
        }

        binding.friendDelCv.setOnClickListener {
            if (!isSelectMode) {
                isSelectMode = true
                binding.friendRank1CheckIv.visibility = View.VISIBLE
                binding.friendRank2CheckIv.visibility = View.VISIBLE
                binding.friendRank3CheckIv.visibility = View.VISIBLE

                binding.friendDelTv.text = "돌아가기"
                binding.friendSetupIv.setImageResource(R.drawable.fragment_friend_go_del)
            }
            else {
                isSelectMode = false
                binding.friendRank1CheckIv.visibility = View.GONE
                binding.friendRank2CheckIv.visibility = View.GONE
                binding.friendRank3CheckIv.visibility = View.GONE

                binding.friendDelTv.text = "친구삭제"
                binding.friendSetupIv.setImageResource(R.drawable.item_home_header_setup)
            }
            rvAdapter.notifyDataSetChanged()
        }

        binding.friendDelTv.setOnClickListener {
            if (!isSelectMode) {
                isSelectMode = true
                binding.friendRank1CheckIv.visibility = View.VISIBLE
                binding.friendRank2CheckIv.visibility = View.VISIBLE
                binding.friendRank3CheckIv.visibility = View.VISIBLE

                binding.friendDelTv.text = "돌아가기"
                binding.friendSetupIv.setImageResource(R.drawable.fragment_friend_go_del)
            }
            else {
                isSelectMode = false
                binding.friendRank1CheckIv.visibility = View.GONE
                binding.friendRank2CheckIv.visibility = View.GONE
                binding.friendRank3CheckIv.visibility = View.GONE

                binding.friendDelTv.text = "친구삭제"
                binding.friendSetupIv.setImageResource(R.drawable.item_home_header_setup)
            }
            rvAdapter.notifyDataSetChanged()

        }

        binding.friendSetupIv.setOnClickListener {
            if(isSelectMode) {//삭제일때
                isSelectMode = false
                otherChecked = rvAdapter.getDelList()
                DeleteFriendDialog(top3Checked, otherChecked, mainContext).show(childFragmentManager.beginTransaction(), "DeleteFriendDialog")
            }
            else {//설정일때
                SetupDialog(this, mainContext).show(childFragmentManager.beginTransaction(), "SetupDialog")
            }
        }

    }

    override fun onRankGetSuccess(data: GetFriendsRank) {
        val friendArr = data.result
        val db = FriendDatabase.getInstance(mainContext)!!

        if (friendArr?.size != 0) {

            val conn = RetroService
            conn.setfcData(this)

            if (friendArr?.size!! <= 3) {
                binding.friendTopCl.visibility = View.VISIBLE
                binding.friendRankOtherRv.visibility = View.GONE

                db.friendDao().delAll()
                PrefApp.pref.setPrefname("user")

                tempScore = friendArr[0].score.toString()
                RetroService.reqConn(
                    ReqConn(
                        PrefApp.pref.getString("email"),
                        friendArr[0].friendEmail!!
                    ), tempScore + "/fir"
                )

                if (friendArr?.size!! >= 2) {
                    tempScore = friendArr[1].score.toString()
                    RetroService.reqConn(ReqConn(PrefApp.pref.getString("email"), friendArr[1].friendEmail!!), tempScore + "/sec")
                    }
                else {

                }
                if (friendArr?.size!! == 3) {
                    tempScore = friendArr[2].score.toString()
                    RetroService.reqConn(ReqConn(PrefApp.pref.getString("email"), friendArr[2].friendEmail!!), tempScore + "/third")
                }


            }

            else {
                binding.friendTopCl.visibility = View.VISIBLE
                binding.friendRankOtherRv.visibility = View.VISIBLE

                db.friendDao().delAll()
                PrefApp.pref.setPrefname("user")
                for (i in friendArr!!) {
                    RetroService.reqConn(ReqConn(PrefApp.pref.getString("email"), i.friendEmail!!), tempScore + "/other")
                }
                val adpater = FriendRankRVAdapter(mainContext, binding)
                binding.friendRankOtherRv.adapter = adpater

                binding.friendRankOtherRv.adapter!!.notifyDataSetChanged()
            }
        }
        else {//Size == 0
            binding.friendTopCl.visibility = View.GONE
            binding.friendRankOtherRv.visibility = View.GONE
            db.friendDao().delAll()
        }
    }

    override fun onRankGetFailure(code: String) {
    }

    override fun onConnGetSuccess(data: GetConn, score: String?) {
        val db = FriendDatabase.getInstance(mainContext)!!

        val tempMail = data.result?.get(0)?.friendEmail
        val tempNic = data.result?.get(0)?.friendNickname
        val scoreArr = score!!.split("/")

        db.friendDao().ins(Friend(tempMail!!, tempNic!!, scoreArr[0]))

        if (scoreArr[1] == "fir") {
            binding.friendRank1MailTv.text = tempMail
            binding.friendRank1NameTv.text = tempNic
            binding.friendRank1RateTv.text = scoreArr[0]
            binding.friendRank2NameTv.text = "---"
            binding.friendRank3NameTv.text = "---"
        }
        else if (scoreArr[1] == "sec") {
            binding.friendRank2MailTv.text = tempMail
            binding.friendRank2NameTv.text = tempNic
            binding.friendRank2RateTv.text = scoreArr[0]
            binding.friendRank3NameTv.text = "---"
        }
        else if (scoreArr[1] == "third") {
            binding.friendRank3MailTv.text = tempMail
            binding.friendRank3NameTv.text = tempNic
            binding.friendRank3RateTv.text = scoreArr[0]
        }
        else {

        }
    }

    override fun onConnGetFailure(code: String) {
    }

    override fun onConnGetLoading() {
    }
}