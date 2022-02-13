package com.umc.clear.ui.login

import com.umc.clear.data.entities.GetLogin
import com.umc.clear.data.entities.GetSignup

interface LoginView {
    fun onLoginGetSuccess(data: GetLogin)
    fun onLoginGetFailure(code: String)
}