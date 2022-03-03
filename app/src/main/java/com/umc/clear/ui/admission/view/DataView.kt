package com.umc.clear.ui.admission.view

import com.umc.clear.data.entities.GetData

interface DataView {
    fun onDataGetSuccess(data: GetData, order: Int)
    fun onDataGetFailure(code: String)
}