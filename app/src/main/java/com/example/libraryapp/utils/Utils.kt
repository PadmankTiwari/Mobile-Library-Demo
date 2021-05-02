package com.example.libraryapp.utils

import android.content.Context
import android.net.ConnectivityManager
import com.example.libraryapp.LibraryApplication

object Utils {

    fun isConnected(): Boolean {
        val connectivityManager =
            LibraryApplication.libraryApplication?.applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}