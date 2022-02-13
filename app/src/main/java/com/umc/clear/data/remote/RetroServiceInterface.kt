package com.umc.clear.data.remote

import com.umc.clear.data.entities.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetroServiceInterface {
    @POST("users/sign-up")
    fun signup(@Body req: ReqSignup): Call<GetSignup>

    @POST("/users/log-in")
    fun login(@Body req: ReqLogin): Call<GetLogin>

    @GET("/friends/ranking?")
    fun friendsRank(
        @Query("userId") userId: Int,
        @Query("year") year: Int,
        @Query("month") month: Int
    ): Call<GetFriendsRank>
}