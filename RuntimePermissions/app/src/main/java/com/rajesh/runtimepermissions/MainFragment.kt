package com.rajesh.runtimepermissions

import android.Manifest
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.rajesh.runtimepermissions.permission.PermissionResultCallback
import com.rajesh.runtimepermissions.permission.PermissionUtils
import java.util.ArrayList
import java.util.HashMap

/**
 * Created by Rajesh on 05/06/19.
 */

class MainFragment : Fragment(), PermissionResultCallback {

    val REQUEST_CODE_PERMISSION = 1000
    val REQUEST_SETTING_PERMISSION = 1002
    private var permissionUtils: PermissionUtils? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView  =  inflater.inflate(R.layout.fragment_main, container, false)

        permissionUtils = PermissionUtils(activity!!, this)

        val button :Button = rootView.findViewById(R.id.button)

        button.setOnClickListener {
            if (!checkPermission())
                requestPermission()
            else
            {
                //Permission already granted
            }
        }



        return rootView
    }

    private fun checkPermission(): Boolean {
        return permissionUtils!!.checkPermission(Manifest.permission.CAMERA)
    }

    private fun requestPermission() {

        val listPermissionsNeeded = HashMap<String, String>()
        listPermissionsNeeded[Manifest.permission.CAMERA] = "CAMERA"
        permissionUtils!!.checkPermission(listPermissionsNeeded, REQUEST_CODE_PERMISSION,this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionUtils!!.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    // overriden methods
    override fun permissionGranted(request_code: Int) {
        MyLog.showLog("Granted")
    }

    override fun partialPermissionGranted(request_code: Int, granted_permissions: ArrayList<String>) {
        MyLog.showLog("partialPermissionGranted")
    }

    override fun permissionDenied(request_code: Int) {
        MyLog.showLog("permissionDenied")
    }

    override fun neverAskAgain(request_code: Int) {
        MyLog.showLog("neverAskAgain")
        PermissionUtils.openSettings(this,activity!!,REQUEST_SETTING_PERMISSION)
    }

}