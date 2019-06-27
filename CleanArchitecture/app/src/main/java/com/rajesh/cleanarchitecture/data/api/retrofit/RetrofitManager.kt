package com.rajesh.cleanarchitecture.data.api.retrofit

import com.rajesh.cleanarchitecture.data.api.ApiHelper
import com.rajesh.cleanarchitecture.data.api.retrofit.entity.MasterDataResponse
import com.rajesh.cleanarchitecture.data.di.DaggerDataComponent
import io.reactivex.Single

class RetrofitManager() : ApiHelper {

    var networkAPI: NetworkAPI

    init {
        networkAPI = DaggerDataComponent.builder().build().getNetworkApi()
    }

    override fun getMasterData(): Single<MasterDataResponse> {
        return networkAPI.getMasterData()
    }
}