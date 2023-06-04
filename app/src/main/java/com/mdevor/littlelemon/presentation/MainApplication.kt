package com.mdevor.littlelemon.presentation

import android.app.Application
import com.mdevor.littlelemon.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
            androidContext(this@MainApplication)
        }
    }
}