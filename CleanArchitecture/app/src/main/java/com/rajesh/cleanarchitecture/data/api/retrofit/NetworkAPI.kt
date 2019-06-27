package com.rajesh.cleanarchitecture.data.api.retrofit

import com.rajesh.cleanarchitecture.data.api.retrofit.entity.MasterDataResponse
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkAPI {

    @GET("path")
    fun getMasterData(): Single<MasterDataResponse>

}