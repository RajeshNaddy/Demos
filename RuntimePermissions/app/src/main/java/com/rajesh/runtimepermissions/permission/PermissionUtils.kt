package com.rajesh.runtimepermissions.permission

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Fragment
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.text.Spanned
import android.text.TextUtils
import android.util.Log
import java.util.*

/**
 * Created by Rajesh on 04/06/19.
 */

open class PermissionUtils(internal var context: Activity, private var permissionResultCallback: PermissionResultCallback) {


    companion object {

        fun openSettings(activity: Activity, requestSettingPermission: Int) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", activity.packageName, null)
            intent.data = uri
            activity.startActivityForResult(intent, requestSettingPermission);
       }
        fun openSettings(fragment: android.support.v4.app.Fragment,activity: Activity, requestSettingPermission: Int) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", activity.packageName, null)
            intent.data = uri
            fragment.startActivityForResult(intent, requestSettingPermission);
        }
    }
    private var permissionsMapWithDescription: HashMap<String, String>? = null
    private var listPermissionsNeeded = ArrayList<String>()
    private var reqCode: Int = 0
    private lateinit var extraData: String

    fun checkPermission(permissions: HashMap<String, String>, request_code: Int) {
        checkPermission(permissions, request_code, "", null)
    }

    fun checkPermission(permissions: HashMap<String, String>, request_code: Int, fragment : android.support.v4.app.Fragment) {
        checkPermission(permissions, request_code, "", fragment)
    }

    private fun checkPermission(permissions: HashMap<String, String>, request_code: Int, extra_data: String, fragment : android.support.v4.app.Fragment?) {
        this.permissionsMapWithDescription = permissions
        this.reqCode = request_code
        this.extraData = extra_data

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkAndRequestPermissions(fragment)) {
                permissionResultCallback.permissionGranted(request_code)
                Log.i("all permissions", "granted")
                Log.i("proceed", "to callback")
            }
        } else {
            permissionResultCallback.permissionGranted(request_code)

            Log.i("all permissions", "granted")
            Log.i("proceed", "to callback")
        }

    }


    fun checkPermission(permissionName: String) : Boolean{
        return if (Build.VERSION.SDK_INT >= 23) {
            ContextCompat.checkSelfPermission(context, permissionName) == PackageManager.PERMISSION_GRANTED
        }else{
            true
        }
    }

    @SuppressLint("NewApi")
    private fun checkAndRequestPermissions(fragment: android.support.v4.app.Fragment?): Boolean {

        listPermissionsNeeded = ArrayList()
        permissionsMapWithDescription!!.forEach{(permissionName, _)->
            val hasPermission = ContextCompat.checkSelfPermission(context, permissionName)
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permissionName)
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            if(fragment != null)
                fragment.requestPermissions( listPermissionsNeeded.toTypedArray(), reqCode)
            else
                ActivityCompat.requestPermissions( context,listPermissionsNeeded.toTypedArray(), reqCode)
            return false
        }
        return true
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            reqCode -> if (grantResults.isNotEmpty()) {
                val perms = HashMap<String, Int>()

                for (i in permissions.indices) {
                    perms.put(permissions[i], grantResults[i])
                }

                val pendingPermissions = ArrayList<String>()
                val pendingPermissionsDescriptions = StringBuilder()

                listPermissionsNeeded.indices
                        .filter { perms[listPermissionsNeeded[it]] != PackageManager.PERMISSION_GRANTED }
                        .forEach {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(context, listPermissionsNeeded[it])) {
                                pendingPermissions.add(listPermissionsNeeded[it])
                                pendingPermissionsDescriptions.append(permissionsMapWithDescription?.get(listPermissionsNeeded[it]))
                            } else {
                                Log.i("Go to settings", "and enable permissions")
                                permissionResultCallback.neverAskAgain(reqCode)
                                //Toast.makeText(context, "Go to settings and enable permissions", Toast.LENGTH_LONG).show()
                                return
                            }
                        }

                if (!TextUtils.isEmpty(extraData)) pendingPermissionsDescriptions.append("<br>$extraData")
                if (pendingPermissions.size > 0) {
                    /*showMessageOKCancel(
//                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                                Html.fromHtml(pendingPermissionsDescriptions.toString(), Html.FROM_HTML_MODE_LEGACY)
//                            } else {
                                Html.fromHtml(pendingPermissionsDescriptions.toString())
//                            }
                            ,
                            DialogInterface.OnClickListener { _, which ->
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> checkPermission(permissionsMapWithDescription!!, reqCode, extraData)
                                    DialogInterface.BUTTON_NEGATIVE -> {
                                        Log.i("permisson", "not fully given")
                                        if (permissionsMapWithDescription?.size == pendingPermissions.size)
                                            permissionResultCallback.permissionDenied(reqCode)
                                        else
                                            permissionResultCallback.partialPermissionGranted(reqCode, pendingPermissions)
                                    }
                                }
                            })*/
                    if (permissionsMapWithDescription?.size == pendingPermissions.size)
                        permissionResultCallback.permissionDenied(reqCode)
                    else
                        permissionResultCallback.partialPermissionGranted(reqCode, pendingPermissions)
                } else {
                    Log.i("all", "permissions granted")
                    Log.i("proceed", "to next step")
                    permissionResultCallback.permissionGranted(reqCode)

                }
            }
        }
    }

    protected open fun showMessageOKCancel(message: Spanned, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(context)
                .setTitle("Permissions")
                .setMessage(message)
                .setPositiveButton("ok", okListener)
//                .setNegativeButton("Later", okListener)
                .create()
                .show()
    }
}
