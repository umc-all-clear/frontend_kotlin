package com.umc.clear.data.entities

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part


data class ReqAdmission (
    @Part val beforePic: MultipartBody.Part,
    @Part val afterPic: MultipartBody.Part,
    @Part val jsonRequest: jsonReq,
    @Part val jsonRequestContent: jsonReqCont
        )

data class jsonRequest (
    val clientID: String? = null
        )

data class jsonRequestContent (
    val content: String? = null
    )