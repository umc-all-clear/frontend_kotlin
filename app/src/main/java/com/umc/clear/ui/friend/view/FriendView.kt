package com.umc.clear.ui.friend.view

import com.umc.clear.data.entities.GetFriendsRank


interface FriendView {
    fun onRankGetSuccess(data: GetFriendsRank)
    fun onRankGetFailure(code: String)
}