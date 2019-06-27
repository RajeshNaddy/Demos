package com.rajesh.cleanarchitecture.data.di

import com.rajesh.cleanarchitecture.data.api.ApiHelper
import com.rajesh.cleanarchitecture.data.api.retrofit.NetworkAPI
import com.rajesh.cleanarchitecture.data.api.retrofit.di.RetrofitModule
import com.rajesh.cleanarchitecture.view.di.module.GsonModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class,GsonModule::class,RetrofitModule::class])
interface DataComponent {

    fun getApiHelper(): ApiHelper

    fun getNetworkApi(): NetworkAPI

}