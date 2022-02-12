package com.umc.clear.data.remote

import com.umc.clear.data.entities.GetSignup
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface RetroServiceInterface {
    @POST("users/sign-up")
    fun reqSignup(
        @Query("email") email: String,
        @Query("password1") pas1: String,
        @Query("password2") pas2: String,
        @Query("nickname") nickName: String
    ): Call<GetSignup>
}