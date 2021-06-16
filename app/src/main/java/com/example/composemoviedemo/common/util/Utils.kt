package com.example.composemoviedemo.common.util

import android.content.Context
import android.util.Log
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.widget.Toast
import com.example.composemoviedemo.R

object Utils {

    private const val TAG_DEBUG = "ComposeMovie.Debug"
    const val TAG_NETWORK = "ComposeMovie.Network"

    fun logDebug(tag: String, message: String, throwable: Throwable? = null) {
        if (Log.isLoggable(TAG_DEBUG, Log.DEBUG)) {
            if (throwable != null)
                Log.d(tag, message, throwable)
            else
                Log.d(tag, message)
        }
    }

    private fun isNetworkAvailable(context: Context): Int {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.allNetworkInfo
        if (networkInfo.isNotEmpty()) {
            for (i in networkInfo.indices) {
                if (networkInfo[i].state == NetworkInfo.State.CONNECTED) {
                    return 1
                }
            }
        }
        return 0
    }

    fun ensureNetworkAvailable(context: Context, needToast: Boolean = true): Boolean {
        val isAvailable = isNetworkAvailable(context) == 1
        if (!isAvailable && needToast) Toast.makeText(
            context,
            R.string.search_failure,
            Toast.LENGTH_SHORT
        ).show()
        return isAvailable
    }
}