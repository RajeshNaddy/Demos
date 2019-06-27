package com.rajesh.cleanarchitecture.domain.di

import com.rajesh.cleanarchitecture.domain.usecase.DownloadMasterDataUseCase
import dagger.Component

@Component(modules = [UseCaseModule::class])
interface UseCaseComponent {

    fun getDownloadMaserDataUseCase() : DownloadMasterDataUseCase

}