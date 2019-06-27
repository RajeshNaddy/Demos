package com.rajesh.cleanarchitecture.domain.di

import com.rajesh.cleanarchitecture.domain.usecase.DownloadMasterDataUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule{

    @Provides
    fun provideDownloadMasterDataUseCase() : DownloadMasterDataUseCase{
        return DownloadMasterDataUseCase()
    }
}