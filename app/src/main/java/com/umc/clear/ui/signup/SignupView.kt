package com.umc.clear.ui.signup

import com.umc.clear.data.entities.GetSignup


interface SignupView {
    fun onLoginGetSuccess(data: GetSignup)
    fun onLoginGetFailure()
}