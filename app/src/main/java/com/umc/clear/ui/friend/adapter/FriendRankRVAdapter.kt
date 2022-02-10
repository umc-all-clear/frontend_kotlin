package com.umc.clear.ui.friend.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.clear.R
import com.umc.clear.data.local.FriendDao
import com.umc.clear.data.local.FriendDatabase
import com.umc.clear.data.local.Pair2
import com.umc.clear.databinding.FragmentFriendBinding
import com.umc.clear.databinding.ItemFriendListBinding
import com.umc.clear.ui.friend.view.FriendFragment
import kotlin.concurrent.thread

class FriendRankRVAdapter(val mainAct: Context, val parBinding: FragmentFriendBinding): RecyclerView.Adapter<FriendRankRVAdapter.ViewHolder>() {

    lateinit var onlistener: onListener
    var isSelectMode = false
    var selectedData = ArrayList<Pair2>()
    val db = FriendDatabase.getInstance(mainAct)!!
    var firstCall = true

    interface onListener {
        fun onClick(stat: Boolean, pos: Int): Boolean
    }

    fun setOnListen(data: onListener) {
        onlistener = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendRankRVAdapter.ViewHolder {
        val binding = ItemFriendListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        if (firstCall) {
            for (i in 1..db.friendDao().getAll().size - 3) {
                selectedData.add(Pair2(false, "a"))
            }
            firstCall = false
        }

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendRankRVAdapter.ViewHolder, position: Int) {
        holder.init(position)
    }

    override fun getItemCount(): Int {
        return db.friendDao().getAll().size - 3

    }

    inner class ViewHolder(val binding: ItemFriendListBinding): RecyclerView.ViewHolder(binding.root) {
        fun init(pos: Int) {
            val list = db.friendDao().getAll()
            binding.itemFriNameTv.text = list[pos + 3].name
            binding.itemFriMailTv.text = list[pos + 3].mail
            binding.itemFriRateTv.text = list[pos + 3].avgRate
            binding.itemFriRankTv.text = (pos + 3).toString()
            binding.itemFriCheckIv.setImageResource(
                if(selectedData[pos].isChecked) {
                R.drawable.dialog_add_friend_check_selected
            }
            else {
                R.drawable.dialog_add_friend_check
            })

            binding.itemFriCheckIv.setOnClickListener {
                if (!selectedData[pos].isChecked) {
                    binding.itemFriCheckIv.setImageResource(R.drawable.dialog_add_friend_check)
                }
                else {
                    binding.itemFriCheckIv.setImageResource(R.drawable.dialog_add_friend_check_selected)
                }
                selectedData[pos].isChecked = onlistener.onClick(selectedData[pos].isChecked, pos)
            }

            parBinding.friendDelIv.setOnClickListener {
                if (!isSelectMode) {
                    isSelectMode = true
                    parBinding.friendRank1CheckIv.visibility = View.VISIBLE
                    parBinding.friendRank2CheckIv.visibility = View.VISIBLE
                    parBinding.friendRank3CheckIv.visibility = View.VISIBLE

                    parBinding.friendDelIv.setImageResource(R.drawable.fragment_friend_go_back_to_norm)
                    parBinding.friendSetupIv.setImageResource(R.drawable.fragment_friend_go_del)
                    binding.itemFriCheckIv.visibility = View.VISIBLE
                }
                else {
                    isSelectMode = false
                    parBinding.friendRank1CheckIv.visibility = View.GONE
                    parBinding.friendRank2CheckIv.visibility = View.GONE
                    parBinding.friendRank3CheckIv.visibility = View.GONE

                    parBinding.friendDelIv.setImageResource(R.drawable.fragment_friend_del)
                    parBinding.friendSetupIv.setImageResource(R.drawable.item_home_header_setup)
                    binding.itemFriCheckIv.visibility = View.GONE
                }
                notifyDataSetChanged()
            }
        }
    }

    fun getDelList(): ArrayList<Pair2>{
        return selectedData
    }
}