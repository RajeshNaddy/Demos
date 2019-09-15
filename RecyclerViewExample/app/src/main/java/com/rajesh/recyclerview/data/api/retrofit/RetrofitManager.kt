package com.rajesh.recyclerview.data.api.retrofit

import com.rajesh.recyclerview.RVApplication
import com.rajesh.recyclerview.data.api.ApiHelper
import com.rajesh.recyclerview.data.api.retrofit.entity.UserListResponse
import io.reactivex.Single

class RetrofitManager(
    private var networkAPI: NetworkAPI = RVApplication.dataComponent.getNetworkApi()
) : ApiHelper {
    override fun getUserList(): Single<UserListResponse> {
        return networkAPI.getUserList()
    }

}