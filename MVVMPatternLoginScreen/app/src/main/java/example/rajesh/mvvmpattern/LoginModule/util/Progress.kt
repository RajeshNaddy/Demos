package com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.util

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.Window
import example.rajesh.mvvmpattern.R

class Progress(context: Context) {

    private var dialog: AlertDialog

    init {
        val builder = AlertDialog.Builder(context)
        builder.setView(LayoutInflater.from(context).inflate(R.layout.progress_dialog_layout, null))
        dialog = builder.create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

    }


    fun show() = dialog.show()

    fun isShowing(): Boolean = dialog.isShowing

    fun dismiss() = dialog.dismiss()

    fun setCancelable(bol: Boolean) = dialog.setCancelable(bol)
}