package com.umc.clear.ui.dialog

import com.umc.clear.data.entities.GetFriendConn


interface SetFriendView {
    fun onFriendConnGetSuccess(data: GetFriendConn)
    fun onFriendConnGetFailure(code: String)
}