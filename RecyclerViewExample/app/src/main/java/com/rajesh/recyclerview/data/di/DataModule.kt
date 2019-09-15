package com.rajesh.recyclerview.data.di


import com.rajesh.recyclerview.data.api.ApiHelper
import com.rajesh.recyclerview.data.api.retrofit.RetrofitManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideApiHelper(): ApiHelper {
        return RetrofitManager()
    }

}

