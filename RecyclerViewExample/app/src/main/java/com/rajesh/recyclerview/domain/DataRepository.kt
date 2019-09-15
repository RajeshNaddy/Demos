package com.rajesh.recyclerview.domain


import com.rajesh.recyclerview.RVApplication
import com.rajesh.recyclerview.data.api.retrofit.entity.UserModel
import com.rajesh.recyclerview.domain.dto.UserDTO
import io.reactivex.Single

class DataRepository {

    private val apiHelper = RVApplication.dataComponent.getApiHelper()

    fun getUserList(): Single<ArrayList<UserDTO>> {
        return apiHelper
            .getUserList()
            .flatMap { userListResponse ->
                var userDTOList = ArrayList<UserDTO>()
                val userList: ArrayList<UserModel>? = userListResponse.userList
                if (!userList.isNullOrEmpty()) {
                    for (user in userList) {
                        val userDTO = UserDTO()
                        userDTO.userEmail = user.userEmail
                        user.userName?.let {
                            userDTO.firstName = user.userName!!.first
                            userDTO.lastName = user.userName!!.last
                        }

                        user.userLocation?.let {
                            userDTO.street = user.userLocation!!.street
                            userDTO.city = user.userLocation!!.city
                            userDTO.state = user.userLocation!!.state
                            userDTO.postcode = user.userLocation!!.postcode
                        }

                        user.userProfile?.let {
                            userDTO.profileLarge = user.userProfile!!.large
                            userDTO.profileMedium = user.userProfile!!.medium
                            userDTO.profileThumbnail = user.userProfile!!.thumbnail
                        }
                        userDTOList.add(userDTO)
                    }
                }
                return@flatMap Single.just(userDTOList)
            }
    }

}