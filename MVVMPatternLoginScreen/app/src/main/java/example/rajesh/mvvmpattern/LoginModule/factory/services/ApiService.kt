package com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.factory.services
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.response.LoginResponseEntity
import retrofit2.Call
import retrofit2.http.*


private const val EMAIL_FIELD: String = "email"
private const val PASSWORD_FIELD: String = "password"
private const val DEVICE_FIELD: String = "device_token"
private const val HEADER_DEVICE_TYPE = "deviceType"
private const val HEADER_AUTHORIZATION = "Authorization"

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Header(HEADER_DEVICE_TYPE) deviceType: String,@Field(
            EMAIL_FIELD
        ) email: String, @Field(PASSWORD_FIELD) password: String, @Field(DEVICE_FIELD) regID: String
    ): Call<LoginResponseEntity>

    @GET("some path")
     fun getBookingID(@Header(HEADER_AUTHORIZATION) token: String,@Path(value = "path if any", encoded = true) booking_id :String
     ): Call<LoginResponseEntity>

}