package com.rajesh.cleanarchitecture.view.activity

import com.rajesh.cleanarchitecture.R
import com.rajesh.cleanarchitecture.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {


    override val layoutId = R.layout.activity_splash

    override fun init() {

    }

    private fun navigateToNextActivity() {
        activityNavigator.navigateToDownload(this)
    }
}
