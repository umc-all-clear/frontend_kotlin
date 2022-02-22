package com.umc.clear.ui.dialog

import com.umc.clear.data.entities.GetConn

interface AddFriendView {
    fun onConnGetSuccess(data: GetConn, score: String?)
    fun onConnGetFailure(code: String)
    fun onConnGetLoading()
}