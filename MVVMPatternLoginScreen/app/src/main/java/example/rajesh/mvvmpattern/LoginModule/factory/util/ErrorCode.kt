package com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.util

import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.ApiResponse
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.Error
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.factory.RetrofitFactory
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback


object ErrorCode{

    const val TRYING = "Trying again please wait"

     fun <R> getErrorResponse(
        statusCode: Int,
        call: Call<R>,
        callback: Callback<R>,
        requestCount: Int,
        isRetry: Boolean ,
        errorBody : ResponseBody?
    ): ApiResponse<R, Error> {
        val Error: Error?
        if (isRetry) {
            if (requestCount == 3) {
                Error = getError(errorBody,statusCode)
                //Timber.d(Error.errorMessage)
            } else {
                Error = Error(statusCode, TRYING)
                call.clone().enqueue(callback)
            }
        } else {
            Error = getError(errorBody,statusCode)
        }
        return ApiResponse(null, Error)
    }
    fun <R> getErrorResponse(
        statusCode: Int,
        call: Call<R>,
        callback: Callback<R>,
        requestCount: Int,
        isRetry: Boolean
    ): ApiResponse<R, Error> {
        val Error: Error?
        if (isRetry) {
            if (requestCount == 3) {
                Error = getError(statusCode)
                //Timber.d(Error.errorMessage)
            } else {
                Error = Error(statusCode, TRYING)
                call.clone().enqueue(callback)
            }
        } else {
            Error = getError(statusCode)
        }
        return ApiResponse(null, Error)
    }

    fun getError(errorBody: ResponseBody?, statusCode: Int): Error {
        if(errorBody != null){
            try {
                val jObjError = JSONObject(errorBody.string())
                return Error(statusCode, jObjError.optString("message"))
            } catch (e: Exception) {
                return Error(statusCode, "Something went wrong!")
            }
        }
        return Error(100, "Something went wrong!")
    }

     fun getError(statusCode: Int): Error {
        val errorMessage = when (statusCode) {
            HTTPStatus.BAD_REQUEST -> "Bad Request"
            HTTPStatus.REQUEST_TIME_OUT -> "Request time out"
            HTTPStatus.UNAUTHORIZED -> "Unauthorized user"
            HTTPStatus.FORBIDDEN -> "Forbidden"
            HTTPStatus.PAGE_NOT_FOUND -> "Page not found"
            HTTPStatus.INTERNAL_SERVER_ERROR -> "Internal serve error"
            HTTPStatus.TOKEN_EXPIRED -> "Token is Expired"
            RetrofitFactory.RETROFIT_ERROR -> "Something went wrong!"
            else -> "Something went wrong!, ERROR_CODE: $statusCode"
        }

        return Error(statusCode, errorMessage)
    }

}