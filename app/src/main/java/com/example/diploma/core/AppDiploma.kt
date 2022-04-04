package com.example.diploma.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppDiploma: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppDiploma)
            modules(listOf(module, vmModule))
        }
    }
}