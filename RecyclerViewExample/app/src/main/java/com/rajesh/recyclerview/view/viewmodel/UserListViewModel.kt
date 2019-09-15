package com.rajesh.recyclerview.view.viewmodel

import androidx.lifecycle.MutableLiveData
import com.rajesh.recyclerview.domain.dto.UserDTO
import com.rajesh.recyclerview.view.ui.UserUI

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserListViewModel : BaseViewModel() {

    var userListLiveData = MutableLiveData<ArrayList<UserUI>>()

    fun getUserList() {
        //userListLiveData.postValue(BaseUI.loading())
        compositeDisposable.add(
            dataRepository.getUserList()
                .map { userDTOList ->
                    mapUserDTOToUserUI(userDTOList)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ list ->
                    userListLiveData.postValue(list)
                }, {
                    userListLiveData.postValue(null)
                })
        )
    }

    private fun mapUserDTOToUserUI(list: ArrayList<UserDTO>): ArrayList<UserUI> {
        var userList = ArrayList<UserUI>()
        for (user in list) {
            val userUI = UserUI()
            userUI.userEmail = user.userEmail
            userUI.firstName = user.firstName
            userUI.lastName = user.lastName
            userUI.street = user.street
            userUI.city = user.city
            userUI.state = user.state
            userUI.postcode = user.postcode
            userUI.profileLarge = user.profileLarge
            userUI.profileMedium = user.profileMedium
            userUI.profileThumbnail = user.profileThumbnail
            userList.add(userUI)
        }
        return userList
    }
}