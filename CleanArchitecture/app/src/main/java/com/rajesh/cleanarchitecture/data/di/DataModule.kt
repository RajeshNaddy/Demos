package com.rajesh.cleanarchitecture.data.di

import com.rajesh.cleanarchitecture.data.api.ApiHelper
import com.rajesh.cleanarchitecture.data.api.retrofit.RetrofitManager
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