package com.umc.clear.data.entities

data class GetData (
    var isSuccess: Boolean? = false,
    var code: Int? = 0,
    var message: String? = null,
    var result: ArrayList<dataResult>? = null
    )
data class dataResult (
    var postId: Int? = 0,
    var cleanedAt: String? = null,
    var userEmail: String? = null,
    var score: Double? = 0.0,
    var contents: String? = null,
    var comments: String? = null,
    var beforePicUrl: String? = null,
    var afterPicUrl: String? = null,
    var waited: Boolean? = false
)
