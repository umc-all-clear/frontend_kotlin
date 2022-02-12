package com.umc.clear.data.entities

import com.google.gson.annotations.SerializedName

data class GetSignup (
    @SerializedName("isSuccess")
    var isSuccess: Boolean? = null,

    @SerializedName("code")
    var resultCode: String? = null,

    @SerializedName("message")
    var resultMsg: String? = null,

    @SerializedName("result")
    var result: result? = null
)

data class result (
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("nickname")
    val nick: String? = null,

    @SerializedName("jwt")
    val jwt: String? = null
)