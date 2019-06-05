package com.rajesh.runtimepermissions

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rajesh.runtimepermissions.permission.PermissionResultCallback
import com.rajesh.runtimepermissions.permission.PermissionUtils
import java.util.ArrayList
import java.util.HashMap

/**
 * Created by Rajesh on 04/06/19.
 */

class MainActivity : AppCompatActivity(), PermissionResultCallback {

    val REQUEST_CODE_PERMISSION = 103
    val REQUEST_SETTING_PERMISSION = 104
    private var permissionUtils: PermissionUtils? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionUtils = PermissionUtils(this, this)
    }


    // single permission
    fun smsPermissionClick(v: View){
        if (!checkPermission())
            requestPermission()
        else
        {
            //Permission already granted
        }
    }

    private fun checkPermission(): Boolean {
        return permissionUtils!!.checkPermission(Manifest.permission.READ_SMS)
    }

    private fun requestPermission() {

        val listPermissionsNeeded = HashMap<String, String>()
        listPermissionsNeeded[Manifest.permission.READ_SMS] = "READ_SMS"
        permissionUtils!!.checkPermission(listPermissionsNeeded, REQUEST_CODE_PERMISSION)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        permissionUtils!!.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    //multiple permissions
    fun multiplePermissionClick(v: View){
        requestMultiplePermission()
    }

    private fun requestMultiplePermission() {

        val listPermissionsNeeded = HashMap<String, String>()
        listPermissionsNeeded[Manifest.permission.ACCESS_FINE_LOCATION] = "ACCESS_FINE_LOCATION"
        listPermissionsNeeded[Manifest.permission.CALL_PHONE] = "CALL_PHONE"
        listPermissionsNeeded[Manifest.permission.READ_EXTERNAL_STORAGE] = "READ_EXTERNAL_STORAGE"
        permissionUtils!!.checkPermission(listPermissionsNeeded, REQUEST_CODE_PERMISSION)
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
        PermissionUtils.openSettings(this,REQUEST_SETTING_PERMISSION)
    }
}
