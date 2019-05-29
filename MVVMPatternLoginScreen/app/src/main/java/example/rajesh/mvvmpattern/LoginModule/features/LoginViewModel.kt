package com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.features

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.ApiResponse
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.Error
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.request.LoginRequestEntity
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.response.LoginResponseEntity

class LoginViewModel : ViewModel() {


    private val loginRepository = LoginRepository()

    fun requestLogin(loginRequest: LoginRequestEntity): LiveData<ApiResponse<LoginResponseEntity, Error>> {
        return loginRepository.login(loginRequest)
    }


}