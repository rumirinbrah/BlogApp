package com.zzz.blogtemp

import android.app.Application
import com.zzz.blogtemp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BlogApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BlogApp)
            modules(appModule)
        }
    }

}