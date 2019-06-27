package com.rajesh.cleanarchitecture.view

import android.app.Application
import com.rajesh.cleanarchitecture.data.di.DaggerDataComponent
import com.rajesh.cleanarchitecture.data.di.DataComponent
import com.rajesh.cleanarchitecture.view.di.component.ApplicationComponent
import com.rajesh.cleanarchitecture.view.di.component.DaggerApplicationComponent

class Application : Application() {

    companion object{
        lateinit var dataComponent: DataComponent
        lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder().build()
        dataComponent = DaggerDataComponent.builder().build();
    }
}