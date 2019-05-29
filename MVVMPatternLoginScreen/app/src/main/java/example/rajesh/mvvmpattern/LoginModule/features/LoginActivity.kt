package com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.features

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.ApiResponse
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.Error
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.request.LoginRequestEntity
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.entities.response.LoginResponseEntity
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.util.HTTPStatus
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.util.HTTPStatus.SUCCESS
import com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.util.isDeviceOnline
import example.rajesh.mvvmpattern.LoginModule.BaseActivity
import example.rajesh.mvvmpattern.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var usersignInobserver: Observer<ApiResponse<LoginResponseEntity, Error>>

    companion object {
        fun start(from: Activity) {
            from.startActivity(Intent(from, LoginActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initialize()
    }

    private fun initialize() {

            loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

            initSigninObserver()

            loginBt.setOnClickListener {
                if (isValidForm()) {
                    if (isDeviceOnline(this)) {
                        showProgress()
                        val loginRequestEntity =
                            LoginRequestEntity(
                                userNameEd.text.toString().trim(),
                                userPasswordEd.text.toString().trim(),""
                            )
                        loginViewModel.requestLogin(loginRequestEntity).observe(this, usersignInobserver)
                    } else
                        showLongToast("No Internet")
                }
            }
        }

    private fun initSigninObserver() {

        usersignInobserver = Observer { t ->

            dismissProgress()

            if (t?.response != null) {

                var loginResponseEntity = t.response

                if (loginResponseEntity?.status == SUCCESS) {

                    //Move to New Screen

                } else {

                    showToast(loginResponseEntity?.message!!)
                }

            } else {
                var Error = t?.error
                if (Error!!.status == HTTPStatus.UNAUTHORIZED) {
                    showToast("Invalid User")
                } else {
                    showAlertDialog("", Error.errorMessage, "Ok",
                        DialogInterface.OnClickListener { _, _ -> }, false
                    )
                }

            }
        }
    }

    private fun isValidForm(): Boolean {
        var error = 0
        var tempStr = userNameEd.text.toString().trim()
        if (tempStr.isEmpty()) {
            error += 1
            userNameEd.error = "Enter User Name"
            userNameEd.requestFocus()
        }

        tempStr = userPasswordEd.text.toString().trim()
        if (tempStr.isEmpty()) {
            userPasswordEd.error = "Enter Password"
            if (error == 0) userPasswordEd.requestFocus()
            error += 1
        }

        return error == 0
    }

}
