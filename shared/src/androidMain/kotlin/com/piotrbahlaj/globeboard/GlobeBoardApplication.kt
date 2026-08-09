package com.piotrbahlaj.globeboard

import android.app.Application
import com.piotrbahlaj.globeboard.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class GlobeBoardApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@GlobeBoardApplication)
        }
    }
}