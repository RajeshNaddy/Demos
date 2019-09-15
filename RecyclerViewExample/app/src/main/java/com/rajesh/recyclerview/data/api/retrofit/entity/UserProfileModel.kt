package com.rajesh.recyclerview.data.api.retrofit.entity

import com.google.gson.annotations.SerializedName

class UserProfileModel {

    @SerializedName("large")
    var large: String = ""

    @SerializedName("medium")
    var medium: String = ""

    @SerializedName("thumbnail")
    var thumbnail: String = ""
}