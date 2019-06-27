package com.rajesh.cleanarchitecture.view.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.rajesh.cleanarchitecture.domain.di.DaggerUseCaseComponent
import com.rajesh.cleanarchitecture.domain.usecase.DownloadMasterDataUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DownloadDataViewModel() : ViewModel() {

    var downloadMasterDataUseCase: DownloadMasterDataUseCase = DaggerUseCaseComponent.builder().build().getDownloadMaserDataUseCase()

    fun downloadMasterData() {
        downloadMasterDataUseCase.downloadMasterData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({data ->
                /*if(data.lastUpdated == 1234){
                }*/
            },{error ->
                Log.e("Test" , error.message)
            })

    }

}