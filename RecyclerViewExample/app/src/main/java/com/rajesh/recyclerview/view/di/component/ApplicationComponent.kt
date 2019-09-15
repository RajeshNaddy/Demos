package com.rajesh.recyclerview.view.di.component

import com.google.gson.Gson
import com.rajesh.recyclerview.view.di.module.ApplicationModule
import com.rajesh.recyclerview.view.di.module.GsonModule
import com.rajesh.recyclerview.view.navigator.ActivityNavigator

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, GsonModule::class])
interface ApplicationComponent {

    fun getActivityNavigator(): ActivityNavigator

    fun getGson(): Gson

}