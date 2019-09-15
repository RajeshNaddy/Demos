package com.rajesh.recyclerview.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rajesh.recyclerview.R
import com.rajesh.recyclerview.utility.AppConstant.Companion.USER_DETAILS
import com.rajesh.recyclerview.view.adapter.UserListAdapter
import com.rajesh.recyclerview.view.ui.UserUI
import com.rajesh.recyclerview.view.viewmodel.UserListViewModel
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : BaseActivity() {

    private lateinit var viewModel: UserListViewModel
    private var userListAdapter: UserListAdapter? = null

    override val layoutId: Int
        get() = R.layout.activity_user_list

    override fun init() {
        viewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)

        initLoader("Loading...", "", false)
        viewModel.userListLiveData.observe(this, Observer {
            finishLoader()
            it?.let{
                userListAdapter = UserListAdapter(this@UserListActivity, it,
                    object: UserListAdapter.UserItemClickListener{
                        override fun onItemClick(position: Int, user: UserUI) {
                            val bundle = Bundle()
                            bundle.putSerializable(USER_DETAILS, user)
                            activityNavigator.navigateToUserDetail(this@UserListActivity, bundle)
                        }

                    })
                rv_user_list.adapter = userListAdapter
            }
        })
        viewModel.getUserList()
    }

}
