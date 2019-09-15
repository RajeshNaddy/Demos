package com.rajesh.recyclerview.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.rajesh.recyclerview.R
import com.rajesh.recyclerview.utility.AppConstant.Companion.USER_DETAILS
import com.rajesh.recyclerview.view.ui.UserUI

import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : BaseActivity() {

    companion object {
        fun getCallingIntent(context: Context, bundle: Bundle): Intent {
            return Intent(context, UserDetailsActivity::class.java)
                .apply {
                    putExtras(bundle)
                }
        }
    }

    override val layoutId: Int
        get() = R.layout.activity_user_details

    override fun init() {
        intent.extras?.let {
            val user = it.getSerializable(USER_DETAILS) as UserUI
            Glide.with(this@UserDetailsActivity)
                .load(user.profileLarge)
                .placeholder(R.drawable.ic_profile)
                .circleCrop()
                .into(img_user)

            txt_user_name.text = "${user.firstName.toUpperCase()} ${user.lastName.toUpperCase()}"
            txt_user_email.text = user.userEmail
            txt_street.text = user.street
            txt_state.text = user.state
            txt_city_name.text = user.city
            txt_pin_code.text = user.postcode
        }
    }

}
