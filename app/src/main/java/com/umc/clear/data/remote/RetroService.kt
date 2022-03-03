package com.umc.clear.data.remote

import android.util.Log
import com.amitshekhar.server.RequestHandler
import com.umc.clear.data.entities.*
import com.umc.clear.ui.admission.view.AdmissionView
import com.umc.clear.ui.admission.view.DataView
import com.umc.clear.ui.dialog.AddFriendView
import com.umc.clear.ui.dialog.SetFriendView
import com.umc.clear.ui.friend.view.FriendView
import com.umc.clear.ui.login.LoginView
import com.umc.clear.ui.signup.SignupView
import com.umc.clear.utils.PrefApp
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetroService {
    lateinit var SignupData: SignupView
    lateinit var loginData: LoginView
    lateinit var rankData: FriendView
    lateinit var connData: AddFriendView
    lateinit var friendData: SetFriendView
    lateinit var admissionData: AdmissionView
    lateinit var getData: DataView

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

    fun setfcData(data: AddFriendView) {
        connData = data
    }

    fun reqConn(req: ReqConn, score: String?) {
        val retro = makeRetrofit()
        val service = retro.create(RetroServiceInterface::class.java)

        val call = service.conn(req.user1, req.user2)

        connData.onConnGetLoading()

        call.enqueue(object : retrofit2.Callback<GetConn> {
            override fun onResponse(call: Call<GetConn>, response: Response<GetConn>) {
                if (response.isSuccessful) {
                    if (response.body()!!.code == 200) {
                        connData.onConnGetSuccess(response.body()!!, score)
                    }
                    else
                    {
                        connData.onConnGetFailure(response.body()!!.code.toString())
                    }
                }
            }

            override fun onFailure(call: Call<GetConn>, t: Throwable) {
            }
        })
    }

    fun setfsData(data: SetFriendView) {
        friendData = data
    }

    fun reqFriendConn(req: ReqFriendConn) {
        val retro = makeRetrofit()
        val service = retro.create(RetroServiceInterface::class.java)

        val call = service.addFriend(req)

        call.enqueue(object : retrofit2.Callback<GetFriendConn> {
            override fun onResponse(call: Call<GetFriendConn>, response: Response<GetFriendConn>) {
                if (response.isSuccessful) {
                    friendData.onFriendConnGetSuccess(response.body()!!)
                } else {
                    friendData.onFriendConnGetFailure(response.body()!!.code.toString())
                }
            }

            override fun onFailure(call: Call<GetFriendConn>, t: Throwable) {
            }

        })
    }

    fun setAdData(data: AdmissionView) {
        admissionData = data
    }

    fun reqAddmission(req: ReqAdmission) {
        val retro = makeRetrofit()
        val service = retro.create(RetroServiceInterface::class.java)

        val call = service.admission(req.beforePic, req.afterPic, req.jsonRequest, req.jsonRequestContent)

        call.enqueue(object : retrofit2.Callback<GetAdmission> {
            override fun onResponse(call: Call<GetAdmission>, response: Response<GetAdmission>) {
                if (response.isSuccessful) {
                    admissionData.onAdmissionGetSuccess(response.body()!!)
                } else {
                    admissionData.onAdmissionGetFailure(response.toString())
                }
            }

            override fun onFailure(call: Call<GetAdmission>, t: Throwable) {
                Log.d("retroFail", "err")
            }

        })
    }


    fun setData(data: DataView) {
        getData = data
    }

    fun reqData(email: String, req: ReqData, order: Int) {
        val retro = makeRetrofit()
        val service = retro.create(RetroServiceInterface::class.java)

        val call = service.getData(email, req)

        call.enqueue(object : retrofit2.Callback<GetData> {
            override fun onResponse(call: Call<GetData>, response: Response<GetData>) {
                if (response.isSuccessful) {
                    getData.onDataGetSuccess(response.body()!!, order)
                } else {
                    getData.onDataGetFailure(response.toString())
                }
            }

            override fun onFailure(call: Call<GetData>, t: Throwable) {
                Log.d("retroFail", "err")
            }

        })
    }

}