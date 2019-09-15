package com.rajesh.recyclerview.data.api.retrofit

import com.rajesh.recyclerview.data.api.retrofit.entity.UserListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkAPI {

    @GET("api/?results=10")
    fun getUserList(): Single<UserListResponse>

}