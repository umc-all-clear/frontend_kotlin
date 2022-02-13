package com.umc.clear.ui.signup

import com.umc.clear.data.entities.GetLogin
import com.umc.clear.data.entities.GetSignup
import retrofit2.Response


interface SignupView {
    fun onLoginGetSuccess(data: GetSignup)
    fun onLoginGetFailure()
}