package com.raxerz.phonepemc

import android.app.Application
import com.raxerz.phonepemc.di.appModule
import com.raxerz.phonepemc.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class PhonePeMcApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PhonePeMcApp)
            modules(listOf(appModule, viewModelModule))
        }
    }
}