package com.rajesh.recyclerview.data.api

import com.rajesh.recyclerview.data.api.retrofit.entity.UserListResponse
import io.reactivex.Single

interface ApiHelper {

    fun getUserList(): Single<UserListResponse>
}