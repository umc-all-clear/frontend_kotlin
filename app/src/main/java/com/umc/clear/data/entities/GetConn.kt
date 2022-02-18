package com.umc.clear.data.entities

import com.google.gson.annotations.SerializedName

data class GetConn (
    var isSuccess:Boolean? = false,
    var code:Int? = 0,
    var message: String? = null,
    var result: ArrayList<connResult>? = null
    )

data class connResult (
    var state:Int? = 0,
    var friendNickname: String? = null,
    var friendEmail: String? = null,
    @SerializedName("friendIdx") var friendIndex: Int? = null
    )
