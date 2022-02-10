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
import com.umc.clear.databinding.FragmentFriendBinding
import com.umc.clear.databinding.FragmentRankBinding
import com.umc.clear.ui.friend.adapter.FriendRankRVAdapter

class FriendFragment: Fragment() {
    lateinit var binding: FragmentFriendBinding
    lateinit var mainContext: Context
    var top3Checked = ArrayList<Boolean>()
    var otherChecked = ArrayList<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendBinding.inflate(inflater, container, false)

        for (i in 1..3) {
            top3Checked.add(false)
        }
        init()
        return binding.root
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


        val rvAdapter = FriendRankRVAdapter(mainContext)
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
                top3Checked[0] = false
            }
            else {
                binding.friendRank1CheckIv.setImageResource(R.drawable.dialog_add_friend_check_selected)
                binding.friendRank1CheckIv.setTag("selected")
                top3Checked[0] = true
            }
        }
        binding.friendRank2CheckIv.setOnClickListener {
            if (binding.friendRank2CheckIv.tag == "selected") {
                binding.friendRank2CheckIv.setImageResource(R.drawable.dialog_add_friend_check)
                binding.friendRank2CheckIv.setTag("unselected")
                top3Checked[1] = false
            }
            else {
                binding.friendRank2CheckIv.setImageResource(R.drawable.dialog_add_friend_check_selected)
                binding.friendRank2CheckIv.setTag("selected")
                top3Checked[1] = true
            }
        }
        binding.friendRank3CheckIv.setOnClickListener {
            if (binding.friendRank3CheckIv.tag == "selected") {
                binding.friendRank3CheckIv.setImageResource(R.drawable.dialog_add_friend_check)
                binding.friendRank3CheckIv.setTag("unselected")
                top3Checked[2] = false
            }
            else {
                binding.friendRank3CheckIv.setImageResource(R.drawable.dialog_add_friend_check_selected)
                binding.friendRank3CheckIv.setTag("selected")
                top3Checked[2] = true
            }
        }
    }
}