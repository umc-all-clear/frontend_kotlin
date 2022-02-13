package com.umc.clear.data.entities


data class GetLogin (
    var isSuccess: Boolean? = null,
    var code: Int? = null,
    var message: String? = null,
    var result: loginResult? = null
)

data class loginResult (
    var id: Int? = null,
    var email: String? = null,
    var nickname: String? = null,
    var state: Boolean? = null
    )
