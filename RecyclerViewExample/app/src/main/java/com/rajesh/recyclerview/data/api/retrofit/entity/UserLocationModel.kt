package com.rajesh.recyclerview.data.api.retrofit.entity

import com.google.gson.annotations.SerializedName

class UserLocationModel {

    @SerializedName("street")
    var street: String = ""

    @SerializedName("city")
    var city: String = ""

    @SerializedName("state")
    var state: String = ""

    @SerializedName("postcode")
    var postcode: String = ""

}