package com.rajesh.runtimepermissions.permission

import java.util.ArrayList

/**
 * Created by Rajesh on 04/06/19.
 */

interface PermissionResultCallback {
    fun permissionGranted(request_code: Int)
    fun partialPermissionGranted(request_code: Int, granted_permissions: ArrayList<String>)
    fun permissionDenied(request_code: Int)
    fun neverAskAgain(request_code: Int)
}
