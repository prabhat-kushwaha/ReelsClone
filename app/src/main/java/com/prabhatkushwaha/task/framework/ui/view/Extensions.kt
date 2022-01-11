package com.prabhatkushwaha.task.framework.ui.view

import android.app.Activity
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.widget.Toast


fun Activity.showToast(message: String?) {
    Toast.makeText(this, message ?: "", Toast.LENGTH_SHORT).show()
}

 fun Activity.isNetworkAvailable(): Boolean {
    val connectivityManager =
        getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}
