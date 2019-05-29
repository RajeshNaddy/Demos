package com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.features

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.ApiResponse
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.Error
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.request.LoginRequestEntity
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.factory.RetrofitFactory
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.factory.services.ApiService
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.response.LoginResponseEntity
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.util.ErrorCode
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.util.HTTPStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository{

    val DEVICE_TYPE = "android"

    fun login(loginRequest: LoginRequestEntity): LiveData<ApiResponse<LoginResponseEntity, Error>> {
        val mutableData: MutableLiveData<ApiResponse<LoginResponseEntity, Error>> = MutableLiveData()
        var requestCount = 0

        RetrofitFactory.getService("BASE URL", ApiService::class.java)
            .login(DEVICE_TYPE
                ,loginRequest.userName, loginRequest.password,loginRequest.regId)
            .enqueue(object : Callback<LoginResponseEntity> {
                override fun onResponse(call: Call<LoginResponseEntity>, response: Response<LoginResponseEntity>) {
                    mutableData.value = if (response.code() == HTTPStatus.SUCCESS) {
                        requestCount = 0
                        //token from header
                        val token = response.headers().values("TAG")[0]
                        //status from body
                        val status = response.body()?.status
                        ApiResponse(response.body(), null)
                    } else {
                        requestCount += 1
                        ErrorCode.getErrorResponse(response.code(), call, this, requestCount, false,response.errorBody())
                    }
                }

                override fun onFailure(call: Call<LoginResponseEntity>, t: Throwable) {
                    requestCount += 1
                    mutableData.value =
                            ErrorCode.getErrorResponse(
                                RetrofitFactory.RETROFIT_ERROR,
                                call,
                                this,
                                requestCount,
                                false
                            )
                }
            })
        return mutableData
    }

}