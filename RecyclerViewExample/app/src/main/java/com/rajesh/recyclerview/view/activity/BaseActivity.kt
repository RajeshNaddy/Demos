package com.rajesh.recyclerview.view.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rajesh.recyclerview.view.di.component.DaggerApplicationComponent
import com.rajesh.recyclerview.view.navigator.ActivityNavigator


abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutId: Int
    private var progressDialog: ProgressDialog? = null

    lateinit var activityNavigator: ActivityNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        activityNavigator = DaggerApplicationComponent.builder().build().getActivityNavigator()
        init()
    }

    abstract fun init()

    fun initLoader(message: String, title: String, isCancelable: Boolean) {

        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
        }

        progressDialog?.let {
            if(it.isShowing){
                it.setMessage(message)
                it.setCancelable(isCancelable)
                it.setTitle(title)
                it.show()
            }
        }
    }

    fun finishLoader() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
