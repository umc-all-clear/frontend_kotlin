package com.umc.clear.data.remote

import android.database.Observable
import com.umc.clear.data.entities.*
import com.umc.clear.utils.PrefApp
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

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

    @GET("/friends/relation?")
    fun conn(
        @Query("user1") user1: String,
        @Query("user2") user2: String
    ): Call<GetConn>

    @POST("/friends/create")
    fun addFriend(@Body req: ReqFriendConn): Call<GetFriendConn>

    @Multipart
    @POST("/noticeboard/offering")
    fun admission(
        @Part beforePic: MultipartBody.Part,
        @Part afterPic: MultipartBody.Part,
        @Part("jsonRequest") jsonRequest: jsonReq,
        @Part("jsonRequestContent") jsonRequestContent: jsonReqCont): Call<GetAdmission>

    @POST("/noticeboard/user?")
    fun getData (
        @Query("email") email: String,
        @Body req: ReqData
    ): Single<GetData>
}