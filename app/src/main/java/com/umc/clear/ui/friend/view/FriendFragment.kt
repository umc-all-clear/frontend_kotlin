package com.umc.clear.ui.friend.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.clear.R
import com.umc.clear.data.entities.Friend
import com.umc.clear.data.local.FriendDatabase
import com.umc.clear.data.local.Pair2
import com.umc.clear.databinding.FragmentFriendBinding
import com.umc.clear.databinding.FragmentRankBinding
import com.umc.clear.ui.dialog.DeleteFriendDialog
import com.umc.clear.ui.dialog.SetupDialog
import com.umc.clear.ui.friend.adapter.FriendRankRVAdapter
import com.umc.clear.utils.PrefApp

class FriendFragment: Fragment() {
    lateinit var binding: FragmentFriendBinding
    lateinit var mainContext: Context
    var top3Checked = ArrayList<Pair2>()
    var otherChecked = ArrayList<Pair2>()
    var isSelectMode = false

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
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        if (PrefApp.glob.getDel()) {
            PrefApp.glob.setDel(false)
            val db = FriendDatabase.getInstance(mainContext)!!
            val list = db.friendDao().getAll()
            top3Checked.clear()
            for (i in 0..2) {
                top3Checked.add(Pair2(false, list[i].mail))
            }

            otherChecked.clear()
            for (i in 1..otherChecked.size - 3) {
                otherChecked.add(Pair2(false, list[i+2].mail))
            }
            binding.friendRankOtherRv.adapter?.notifyDataSetChanged()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainContext = context
    }
    fun init() {
        val db = FriendDatabase.getInstance(mainContext)!!
        var al = Friend("a", "b", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "ba", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "aa", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "qqba", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "b234a", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "5ba", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "ba6", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "b652a", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "ba1", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "ba11", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "ba21", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "ba31", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "ba41", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "ba51", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "ba61", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "ba17", "ba")
        db.friendDao().ins(al)
        al = Friend("a", "ba81", "ba")
        db.friendDao().ins(al)


        val rvAdapter = FriendRankRVAdapter(mainContext, binding)
        rvAdapter.setOnListen(object : FriendRankRVAdapter.onListener{
            override fun onClick(stat: Boolean, pos: Int):Boolean {
                rvAdapter.notifyItemChanged(pos)
                return !stat
            }
        })
        binding.friendRankOtherRv.adapter = rvAdapter


        binding.friendDelIv.setOnClickListener {
            otherChecked = rvAdapter.getDelList()
        }

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
            SetupDialog(this).show(childFragmentManager.beginTransaction(), "SetupDialog")
        }

//        binding.friendDelIv.setOnClickListener {
//            isSelectMode = true
//            binding.friendRank1CheckIv.visibility = View.VISIBLE
//            binding.friendRank2CheckIv.visibility = View.VISIBLE
//            binding.friendRank3CheckIv.visibility = View.VISIBLE
//
//            binding.friendDelIv.setImageResource(R.drawable.fragment_friend_go_back_to_norm)
//            binding.friendSetupIv.setImageResource(R.drawable.fragment_friend_go_del)
//        }
        binding.friendSetupIv.setOnClickListener {
            if(isSelectMode) {
                isSelectMode = false
                DeleteFriendDialog(top3Checked, otherChecked, mainContext).show(childFragmentManager.beginTransaction(), "DeleteFriendDialog")
            }
            else {
                SetupDialog(this).show(childFragmentManager.beginTransaction(), "SetupDialog")
            }
        }

    }
}