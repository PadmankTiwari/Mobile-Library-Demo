package com.example.libraryapp

import android.app.Application
import com.example.libraryapp.session.SavedPreferences

class LibraryApplication : Application() {
    companion object {
        var libraryApplication: LibraryApplication? = null
        var deviceHeight: Int = 0
        var deviceWidth: Int = 0
    }

    override fun onCreate() {
        super.onCreate()
        libraryApplication = this
        SavedPreferences.init(this)
        deviceHeight = resources.displayMetrics.heightPixels
        deviceWidth = resources.displayMetrics.widthPixels
    }
}