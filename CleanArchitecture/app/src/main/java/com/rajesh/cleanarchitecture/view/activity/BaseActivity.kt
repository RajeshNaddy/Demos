package com.rajesh.cleanarchitecture.view.activity

import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.rajesh.cleanarchitecture.view.Application
import com.rajesh.cleanarchitecture.view.navigator.ActivityNavigator

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    protected val binding: T by lazy { DataBindingUtil.setContentView(this, layoutId) as T }
    abstract val layoutId: Int
    private var progressDialog: ProgressDialog? = null

    lateinit var activityNavigator : ActivityNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        activityNavigator = Application.component.getActivityNavigator()
        init()
    }

    abstract fun init()

    fun initLoader(message: String, title: String, isCancelable: Boolean) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
        }
        progressDialog?.apply {
            if (isShowing) {
                setMessage(message)
                setCancelable(isCancelable)
                setTitle(title)
                show()
            }
        }
    }

    fun finishLoader() {
        if (progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
