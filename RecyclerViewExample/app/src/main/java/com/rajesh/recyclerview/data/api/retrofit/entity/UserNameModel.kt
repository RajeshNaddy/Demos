package com.rajesh.recyclerview.data.api.retrofit.entity

import com.google.gson.annotations.SerializedName

class UserNameModel {

    @SerializedName("title")
    var title: String = ""

    @SerializedName("first")
    var first: String = ""

    @SerializedName("last")
    var last: String = ""

}