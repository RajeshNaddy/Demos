package com.rajesh.cleanarchitecture.view.di.component

import com.google.gson.Gson
import com.rajesh.cleanarchitecture.view.di.module.ApplicationModule
import com.rajesh.cleanarchitecture.view.di.module.GsonModule
import com.rajesh.cleanarchitecture.view.navigator.ActivityNavigator
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, GsonModule::class])
interface ApplicationComponent {

    fun getActivityNavigator(): ActivityNavigator

    fun getGson(): Gson

}