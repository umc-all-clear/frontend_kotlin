package com.umc.clear.ui.admission.view

import com.umc.clear.data.entities.GetAdmission

interface AdmissionView {
    fun onAdmissionGetSuccess(data: GetAdmission)
    fun onAdmissionGetFailure(code: String)
}