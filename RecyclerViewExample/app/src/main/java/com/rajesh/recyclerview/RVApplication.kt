package com.rajesh.recyclerview

import android.app.Application
import com.rajesh.recyclerview.data.di.DaggerDataComponent
import com.rajesh.recyclerview.data.di.DataComponent
import com.rajesh.recyclerview.view.di.component.ApplicationComponent
import com.rajesh.recyclerview.view.di.component.DaggerApplicationComponent
import com.rajesh.recyclerview.view.di.module.ContextModule


class RVApplication : Application() {

    companion object {
        lateinit var dataComponent: DataComponent
        val component: ApplicationComponent = DaggerApplicationComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        dataComponent = DaggerDataComponent.builder().contextModule(ContextModule(this)).build()

    }
}