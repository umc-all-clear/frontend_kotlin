package com.umc.clear.data.entities

data class GetFriendConn(
    var isSuccess:Boolean? = false,
    var code:Int? = 0,
    var message: String? = null,
    var result: friendConnResult? = null

)

data class friendConnResult (
    var state:Int? = 0
    )
