package com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.response

import com.squareup.moshi.Json

data class LoginResponseEntity(

    @Json(name = "message")
    val message: String? = null,

    @Json(name = "status")
    val status: Int? = null



)