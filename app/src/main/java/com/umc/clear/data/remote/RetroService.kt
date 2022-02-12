package com.umc.clear.data.remote

import android.util.Log
import com.umc.clear.data.entities.GetSignup
import com.umc.clear.data.entities.ReqSignup
import com.umc.clear.ui.signup.SignupView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroService {
    lateinit var transData: SignupView

    fun makeRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder().baseUrl("http://3.35.26.181/").addConverterFactory(
            GsonConverterFactory.create()).build()

        return retrofit
    }

    fun setData(view: SignupView) {
        transData = view
    }

    fun reqSignin(req: ReqSignup) {
        val retro = makeRetrofit()
        val service = retro.create(RetroServiceInterface::class.java)

        val call = service.reqSignup(
            req.email,
            req.pas1,
            req.pas2,
            req.nickName
        )

        call.enqueue(object : retrofit2.Callback<GetSignup> {

            override fun onResponse(call: Call<GetSignup>, response: Response<GetSignup>) {
                if (response.isSuccessful) {
                    transData.onLoginGetSuccess(response.body()!!)
                }
                else {
                    Log.d("loginDataerr", response.code().toString())
                    transData.onLoginGetFailure()
                }
            }

            override fun onFailure(call: Call<GetSignup>, t: Throwable) {
                Log.d("loginDataerr", t.message.toString())
                transData.onLoginGetFailure()
            }
        })
    }
}