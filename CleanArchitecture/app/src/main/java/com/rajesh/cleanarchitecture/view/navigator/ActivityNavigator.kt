package com.rajesh.cleanarchitecture.view.navigator

import android.app.Activity
import com.rajesh.cleanarchitecture.view.activity.DownloadActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityNavigator @Inject constructor() {

    fun navigateToDownload(activity: Activity) {
        activity.startActivity(DownloadActivity.getCallingIntent(activity))
        activity.finish()
    }

}