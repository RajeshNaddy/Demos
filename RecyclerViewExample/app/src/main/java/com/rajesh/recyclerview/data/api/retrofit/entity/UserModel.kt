package com.rajesh.recyclerview.data.api.retrofit.entity

import com.google.gson.annotations.SerializedName

class UserModel{

    @SerializedName("name")
    var userName: UserNameModel? = null

    @SerializedName("email")
    var userEmail: String = ""

    @SerializedName("location")
    var userLocation: UserLocationModel? = null

    @SerializedName("picture")
    var userProfile: UserProfileModel? = null

}