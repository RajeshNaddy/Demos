package com.rajesh.cleanarchitecture.domain.usecase

import com.rajesh.cleanarchitecture.domain.dto.MasterDataDTO
import io.reactivex.Single

class DownloadMasterDataUseCase() : BaseUseCase() {

    fun downloadMasterData() : Single<MasterDataDTO>{
        return apiHelper.getMasterData()
            .map({ masterDataResponse -> mGson.fromJson(mGson.toJson(masterDataResponse), MasterDataDTO::class.java) })
    }


}