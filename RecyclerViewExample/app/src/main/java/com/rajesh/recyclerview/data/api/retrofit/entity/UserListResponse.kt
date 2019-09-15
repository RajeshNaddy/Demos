package com.rajesh.recyclerview.data.api.retrofit.entity

import com.google.gson.annotations.SerializedName

class UserListResponse {

    @SerializedName("results")
    var userList: ArrayList<UserModel>? = null
}