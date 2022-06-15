package com.example.diploma.core

import android.app.Application
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppDiploma : Application() {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppDiploma)
            modules(listOf(module, vmModule))
        }
    }
}