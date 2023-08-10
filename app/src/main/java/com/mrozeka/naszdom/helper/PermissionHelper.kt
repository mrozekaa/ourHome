package com.mrozeka.naszdom.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionHelper {
    fun isCallPermissionGranted(context: Context) : Boolean{
        return ContextCompat.checkSelfPermission(context,
            Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED
    }
    fun requestCallPermission(activity: Activity){
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CALL_PHONE), 100)
    }
    fun checkCallPermission(requestCode:Int, grantResults:IntArray, onGrantListener: (Boolean) -> Unit){
        if (requestCode == 100) {
            onGrantListener(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        }
    }

}