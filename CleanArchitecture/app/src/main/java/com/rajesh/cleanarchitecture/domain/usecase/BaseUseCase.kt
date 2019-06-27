package com.rajesh.cleanarchitecture.domain.usecase

import com.google.gson.Gson
import com.rajesh.cleanarchitecture.data.api.ApiHelper
import com.rajesh.cleanarchitecture.view.Application

open class BaseUseCase {

    var mGson: Gson = Application.component.getGson()
    var apiHelper: ApiHelper = Application.dataComponent.getApiHelper()

}