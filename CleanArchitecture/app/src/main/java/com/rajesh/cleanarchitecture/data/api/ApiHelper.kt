package com.rajesh.cleanarchitecture.data.api

import com.rajesh.cleanarchitecture.data.api.retrofit.entity.MasterDataResponse
import io.reactivex.Single

interface ApiHelper {

    fun getMasterData(): Single<MasterDataResponse>
}