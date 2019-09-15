package com.rajesh.recyclerview.data.di

import android.content.Context
import com.rajesh.recyclerview.data.api.ApiHelper
import com.rajesh.recyclerview.data.api.retrofit.NetworkAPI
import com.rajesh.recyclerview.data.api.retrofit.di.RetrofitModule
import com.rajesh.recyclerview.view.di.module.ContextModule
import com.rajesh.recyclerview.view.di.module.GsonModule

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, RetrofitModule::class, GsonModule::class, ContextModule::class])
interface DataComponent {

    fun getApiHelper(): ApiHelper

    fun getNetworkApi(): NetworkAPI

    fun getContext(): Context
}