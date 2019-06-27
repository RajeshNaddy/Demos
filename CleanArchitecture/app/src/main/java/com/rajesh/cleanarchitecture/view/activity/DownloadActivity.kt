package com.rajesh.cleanarchitecture.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import com.rajesh.cleanarchitecture.R
import com.rajesh.cleanarchitecture.databinding.ActivityDownloadBinding
import com.rajesh.cleanarchitecture.view.viewmodel.DownloadDataViewModel

class DownloadActivity : BaseActivity<ActivityDownloadBinding>() {

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, DownloadActivity::class.java)
        }
    }

    override val layoutId = R.layout.activity_download

    private lateinit var downloadDataViewModel: DownloadDataViewModel

    override fun init() {
        downloadDataViewModel = ViewModelProviders.of(this).get(DownloadDataViewModel::class.java)
        downloadDataViewModel.downloadMasterData();
    }
}
