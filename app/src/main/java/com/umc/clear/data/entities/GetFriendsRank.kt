package com.umc.clear.data.entities


class GetFriendsRank (
    var isSuccess: Boolean? = false,
    var code: Int? = 0,
    var message: String? = null,
    var result: ArrayList<rankResult>? = null
)

data class rankResult(
    var friendIndex: Int? = 0,
    var friendEmail: String? = null,
    var score:Double? = 0.0
)