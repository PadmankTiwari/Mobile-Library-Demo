package com.example.libraryapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

class SplashActivity : AppCompatActivity() {
    init {
        lifecycleScope.launchWhenResumed {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}