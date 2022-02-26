package com.umc.clear.data.entities

data class GetAdmission (
    var isSuccess:Boolean? = false,
    var code: Int? = 0,
    var message: String? = null,
    var result: AdmissionResult? = null
)

data class AdmissionResult (
    var queryResult: String? = null,
    var writerID: Int? = 0,
    var writerEmail: String? = null,
    var contents: String? = null,
    var beforePicUrl: String? = null,
    var afterPicUrl: String? = null,
    var waited: Boolean? = false
)
