package com.umc.clear.data.remote

import android.util.Log
import com.umc.clear.data.entities.*
import com.umc.clear.ui.friend.view.FriendView
import com.umc.clear.ui.login.LoginView
import com.umc.clear.ui.signup.SignupView
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetroService {
    lateinit var SignupData: SignupView
    lateinit var loginData: LoginView
    lateinit var rankData: FriendView

    fun makeRetrofit(): Retrofit {
        val clientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder().baseUrl("http://3.35.26.181/").addConverterFactory(
            GsonConverterFactory.create()).client(clientBuilder.build()).build()

        return retrofit
    }

    fun setsData(view: SignupView) {
        SignupData = view
    }

    fun reqSignin(req: ReqSignup) {
        val retro = makeRetrofit()
        val service = retro.create(RetroServiceInterface::class.java)

        val call = service.signup(req)

        call.enqueue(object : retrofit2.Callback<GetSignup> {

            override fun onResponse(call: Call<GetSignup>, response: Response<GetSignup>) {
                if (response.isSuccessful) {
                    SignupData.onLoginGetSuccess(response.body()!!)
                }
                else {
                    Log.d("loginDataerr", response.code().toString())
                    SignupData.onLoginGetFailure()
                }
            }

            override fun onFailure(call: Call<GetSignup>, t: Throwable) {
                Log.d("loginDataerr", t.message.toString())
                SignupData.onLoginGetFailure()
            }
        })
    }

    fun setlData(data: LoginView) {
        loginData = data

    }

    fun reqLogin(req: ReqLogin) {
        val retro = makeRetrofit()
        val service = retro.create(RetroServiceInterface::class.java)

        val call = service.login(req)

        call.enqueue(object: retrofit2.Callback<GetLogin> {
            override fun onResponse(call: Call<GetLogin>, response: Response<GetLogin>) {
                if (response.isSuccessful) {
                    loginData.onLoginGetSuccess(response.body()!!)
                }
                else {
                    loginData.onLoginGetFailure(response.body()!!.code.toString())
                }
            }

            override fun onFailure(call: Call<GetLogin>, t: Throwable) {
            }

        })

    }

    fun setfData(data: FriendView) {
        rankData = data
    }

    fun reqRank(req: ReqFriendsRank) {
        val retro = makeRetrofit()
        val service = retro.create(RetroServiceInterface::class.java)

        val call = service.friendsRank(req.userid, req.year, req.month)

        call.enqueue(object : retrofit2.Callback<GetFriendsRank> {
            override fun onResponse(
                call: Call<GetFriendsRank>,
                response: Response<GetFriendsRank>
            ) {
                if (response.isSuccessful) {
                    rankData.onRankGetSuccess(response.body()!!)
                } else {
                    rankData.onRankGetFailure(response.body()!!.code.toString())
                }
            }

            override fun onFailure(call: Call<GetFriendsRank>, t: Throwable) {
            }
        })
    }
}